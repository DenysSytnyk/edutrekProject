package telran.edutrek.group.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import telran.edutrek.group.dto.GroupDto;
import telran.edutrek.group.dto.GroupRegisterDto;
import telran.edutrek.group.dto.GroupUpdateDto;
import telran.edutrek.group.dto.StudentForGroupDto;
import telran.edutrek.group.entities.GroupData;
import telran.edutrek.group.exceptions.GroupDeactivatedException;
import telran.edutrek.group.exceptions.GroupExistsExceptions;
import telran.edutrek.group.exceptions.GroupNotFoundExceptions;
import telran.edutrek.group.exceptions.StudentExistsInGroupExceptions;
import telran.edutrek.group.exceptions.StudentNotInGroupException;
import telran.edutrek.group.repo.GroupRepository;
import telran.edutrek.student.dto.GroupForStudentDto;
import telran.edutrek.student.dto.StudentDto;
import telran.edutrek.student.entities.StudentContact;
import telran.edutrek.student.repo.StudentRepository;
import telran.edutrek.utils.StatusContact;

@Service
public class GroupService implements IGroupManagement
{
	@Autowired
	GroupRepository repo;
	
	@Autowired
	StudentRepository studRepo;

	@Override
	public GroupDto createGroup(GroupRegisterDto group) 
	{
		if (repo.existsById(group.getName())) 
			throw new GroupExistsExceptions(group.getName());
		
		GroupData data = GroupRegisterDto.build(group);
		
		if (data.getStudents() !=null) 
			addGroupForStudents(data.getStudents(), GroupForStudentDto.toGroup(data));

		repo.save(data);
		return data.build();
	}

	private void addGroupForStudents(List<StudentForGroupDto> students, GroupForStudentDto group) 
	{
		students.forEach(s -> 
		{
			StudentContact stud = getStudent(s.getId());
			if (stud.getGroup() != null) 
			{
				stud.getGroup().add(group);
			}
			else {
				stud.setGroup(new ArrayList<GroupForStudentDto>(Arrays.asList(group)));
			}
			
			studRepo.save(stud);
		});
	}

	@Override
	public GroupDto updateGroupById(String id, GroupUpdateDto newGroupData) 
	{
	
		GroupData data = getGroup(id);
		if (data.isDeactivate())
			throw new GroupDeactivatedException(data.getName());
		
		data.setName(newGroupData.getName());
		data.setWhatsapp(newGroupData.getWhatsapp());
		data.setSkype(newGroupData.getSkype());
		data.setSlack(newGroupData.getSlack());
		data.setStartDate(newGroupData.getStartDate());
		data.setEndDate(newGroupData.getEndDate());
		data.setLessons(newGroupData.getLessons());
		data.setWebinars(newGroupData.getWebinars());
		data.setReminder(newGroupData.getReminder());
		data.setDeactivate(newGroupData.isDeactivate());
		data.setStudents(newGroupData.getStudents());
		
		repo.save(data);
		return data.build();
	}

	private GroupData getGroup(String id) 
	{
		return repo.findById(id).orElseThrow(() -> 
		new GroupNotFoundExceptions(id));
	}
	
	private StudentContact getStudent(String id) 
	{
		return studRepo.findById(id).orElseThrow(() -> 
		new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found student"));
	}

	@Override
	public boolean addStudent(String groupId, String studentId) 
	{
		GroupData data = getGroup(groupId);
		StudentContact student = getStudent(studentId);
		
		if (data.isDeactivate())
			throw new GroupDeactivatedException(data.getName());
		
		List<StudentForGroupDto> list = data.getStudents();
		if (list == null)
			list = new ArrayList<StudentForGroupDto>();
		if (list.stream().anyMatch(s -> s.getId().equals(studentId))) 
			throw new StudentExistsInGroupExceptions(groupId, student.getSurName());
	
		list.add(StudentForGroupDto.toStudent(student));
		if (student.getGroup() != null) 
		{
			student.getGroup().add(GroupForStudentDto.toGroup(data));
		}
		else {
			student.setGroup(new ArrayList<GroupForStudentDto>(Arrays.asList(GroupForStudentDto.toGroup(data))));
		}
		data.setStudents(list);
		
		repo.save(data);
		studRepo.save(student);
		
		return true;
	}

	@Override
	public boolean moveStudent(String groupId, String studentId) 
	{
		StudentContact student = getStudent(studentId);
		GroupData group = getGroup(groupId);
		if (group.isDeactivate())
			throw new GroupDeactivatedException(group.getName());
		
		List<StudentForGroupDto> list = group.getStudents();
		
		if (list.stream().anyMatch(s -> s.getId().equals(studentId))) 
			throw new StudentExistsInGroupExceptions(groupId, student.getSurName());
		
		student.getGroup().add(GroupForStudentDto.toGroup(group));
		addStudent(groupId, studentId);
		
		studRepo.save(student);
		
		return true;
	}

	@Override
	public StudentDto archiveStudentFromGroup(String groupId, String studentId) 
	{
		StudentContact student = getStudent(studentId);
		GroupData group = getGroup(groupId);
		if (group.isDeactivate())
			throw new GroupDeactivatedException(group.getName());
		
		if (!student.getGroup().stream().anyMatch((g) -> g.getId().equals(group.getId()))) 
			throw new StudentNotInGroupException(groupId, student.getSurName());
		
		group.getStudents().remove(StudentForGroupDto.toStudent(student));
		repo.save(group);
		
		student.setGroup(null);
		student.setStatusContact(StatusContact.ARCHIVE);
		studRepo.save(student);
		
		return student.build();
	}

	@Override
	public boolean deactivateGroupById(String id) 
	{
		GroupData group = getGroup(id);
		if (group.isDeactivate())
			throw new GroupDeactivatedException(group.getName());
		
		if (ChronoUnit.DAYS.between(group.getEndDate(), LocalDate.now()) > 30) 
		{
			group.setStatus(true);
			repo.save(group);
		}
			
		return true;
	}

	@Override
	public GroupDto getGroupById(String id) 
	{
		return getGroup(id).build();
	}

	@Override
	public List<GroupDto> getAllGroups() 
	{
		return repo.findAll().stream().map(gd -> gd.build()).toList();
	}

	@Override
	public GroupDto deleteGroup(String id) 
	{
		GroupData group =  repo.findById(id).orElseThrow(() -> 
		new GroupNotFoundExceptions(id));
		
		repo.deleteById(id);
		return group.build();
	}

	@Override
	public boolean removeStudentFromGroup(String groupId, String studentId)
	{
		StudentContact student = getStudent(studentId);
		GroupData group = getGroup(groupId);
		
		
		if (!student.getGroup().stream().anyMatch((g) -> g.getId().equals(group.getId()))) 
			throw new StudentNotInGroupException(groupId, student.getSurName());
	
		group.getStudents().removeIf((s) -> s.getId().equals(studentId));
		student.getGroup().removeIf((g) -> g.getId().equals(groupId));
		
		repo.save(group);
		studRepo.save(student);
		
		return true;
	}

	@Override
	public GroupDto getGroupByName(String name) 
	{
		return repo.findByName(name).orElseThrow(() -> 
		new GroupNotFoundExceptions(name));
	}

}
