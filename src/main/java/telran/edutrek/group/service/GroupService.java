package telran.edutrek.group.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import telran.edutrek.group.dto.GroupDto;
import telran.edutrek.group.dto.GroupUpdateDto;
import telran.edutrek.group.entities.GroupData;
import telran.edutrek.group.exceptions.GroupExistsExceptions;
import telran.edutrek.group.exceptions.GroupNotFoundExceptions;
import telran.edutrek.group.exceptions.StudentExistsInGroupExceptions;
import telran.edutrek.group.exceptions.StudentNotInGroupException;
import telran.edutrek.group.repo.GroupRepository;
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
	public GroupDto createGroup(GroupDto group) 
	{
		if (repo.existsById(group.getId())) 
		throw new GroupExistsExceptions(group.getId());
		
		GroupData data = GroupDto.build(group);
		repo.save(data);
		return group;
	}

	@Override
	public GroupDto updateGroupById(String id, GroupUpdateDto newGroupData) 
	{
		GroupData data = getGroup(id);
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
		
		List<StudentDto> list = data.getStudents();
		if (list.stream().anyMatch(s -> s.getId() == studentId)) 
			throw new StudentExistsInGroupExceptions(groupId, student.getSurName());
		list.add(student.build());
		
		data.setStudents(list);
		repo.save(data);
		
		return true;
	}

	@Override
	public boolean moveStudent(String groupId, String studentId) 
	{
		StudentContact student = getStudent(studentId);
		GroupData group = getGroup(groupId);
		
		if (student.getGroup() !=null) 
		{
			GroupData currentGroup = getGroup(student.getGroup().getId());
			currentGroup.getStudents().remove(student.build());
			repo.save(currentGroup);
		}
		
		student.setGroup(group.build());
		addStudent(groupId, studentId);
		
		return true;
	}

	@Override
	public StudentDto archiveStudentFromGroup(String groupId, String studentId) 
	{
		StudentContact student = getStudent(studentId);
		GroupData group = getGroup(groupId);
		
		if (student.getGroup().getId() != group.getId()) 
			throw new StudentNotInGroupException(groupId, student.getSurName());
		
		group.getStudents().remove(student.build());
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

}
