package telran.edutrek.lecturers.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telran.edutrek.lecturers.dto.LecturersDto;
import telran.edutrek.lecturers.dto.LecturersRegisterDto;
import telran.edutrek.lecturers.dto.LecturersUpdateDto;
import telran.edutrek.lecturers.entities.LecturersContact;
import telran.edutrek.lecturers.exeptions.GroupToLecturerExistsException;
import telran.edutrek.lecturers.exeptions.GroupToLecturerNotExistsException;
import telran.edutrek.lecturers.exeptions.LecturerExistsException;
import telran.edutrek.lecturers.exeptions.LecturerNotFoundException;
import telran.edutrek.lecturers.repo.LecturerRepository;
import telran.edutrek.student.dto.GroupForStudentDto;

@Service
public class LecturersService implements ILecturersManagement{
	
	@Autowired
	LecturerRepository repo;
	

	@Override
	public LecturersDto createLecturer(LecturersRegisterDto lecturer) {
		if(repo.existsById(lecturer.getPhone())) {
			throw new LecturerExistsException(lecturer.getPhone());
		}
		LecturersContact lecturerContact=new LecturersContact(lecturer.getName(), lecturer.getSurName(), lecturer.getPhone(),
				lecturer.getEmail(), lecturer.getCity(), null, lecturer.getSourse(), lecturer.getComment(),
				lecturer.getStatusContact(), lecturer.getGroup(), new LinkedList<String>());
		repo.save(lecturerContact);
		return lecturerContact.build();
	}

	@Override
	public LecturersDto removeLecturerById(String id) {
		LecturersContact lecturer=getLecturer(id);
		repo.delete(lecturer);
		return lecturer.build();
	}

	@Override
	public LecturersDto updateLecturer(String id, LecturersUpdateDto  newLecturer) {
		if(!repo.existsById(id))
			throw new LecturerNotFoundException(id);
		LecturersContact user=getLecturer(newLecturer.getPhone());
		if(newLecturer.getName()==null) {
			user.setName(user.getName());
		}
		if(newLecturer.getSurName()==null) {
			user.setSurName(user.getSurName());
		}
		if(newLecturer.getPhone()==null) {
			user.setPhone(user.getPhone());
		}
		if(newLecturer.getEmail()==null) {
			user.setEmail(user.getEmail());
		}
		if(newLecturer.getCity()==null) {
			user.setCity(user.getCity());
		}
		if(newLecturer.getStatusContact()==null) {
			user.setStatusContact(user.getStatusContact());
		}
		if(newLecturer.getGroup()==null) {
			user.setGroup(user.getGroup());
		}
		user.setName(newLecturer.getName());
		user.setPhone(newLecturer.getPhone());
		user.setEmail(newLecturer.getEmail());
		user.setCity(newLecturer.getCity());
		user.setGroup(newLecturer.getGroup());
		user.setStatusContact(newLecturer.getStatusContact());
		repo.save(user);
		return user.build();
	}

	@Override
	public LecturersDto getLecturerById(String id) {
		return getLecturer(id).build();
		
	}
	
	public LecturersContact getLecturer(String id) {
		return repo.findById(id).orElseThrow(()-> new LecturerNotFoundException(id));
		
	}

	@Override
	public List<LecturersDto> getAllLecturer() {
		List<LecturersDto> lecturer=repo.findAll().stream().map(m->m.build()).collect(Collectors.toList());
		return lecturer;
	}

	@Override
	public List<LecturersDto> getLecturerByName(String name) {
		
		return getAllLecturer().stream().filter(l->l.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
	}

	@Override
	public LecturersDto addGroupToLecturer(String id, GroupForStudentDto group) {
		LecturersContact lecturer=getLecturer(id);	
		List<GroupForStudentDto> list =lecturer.getGroup();
		if(list==null) {
			list=new ArrayList<GroupForStudentDto>();
		}
		if(list.stream().anyMatch(g->g.getId().equals(group.getId()))) {
			throw new GroupToLecturerExistsException(group.getName());
		}
		list.add(group);
		lecturer.setGroup(list);
		repo.save(lecturer);
		return lecturer.build();
	}

	@Override
	public LecturersDto removeGroupToLecturer(String id, GroupForStudentDto group) {
		LecturersContact lecturer=getLecturer(id);	
		List<GroupForStudentDto> list =lecturer.getGroup();
		if(list==null) {
			list=new ArrayList<GroupForStudentDto>();
		}
		if(!list.stream().anyMatch(g->g.getId().equals(group.getId()))) {
			throw new GroupToLecturerNotExistsException(group.getName());
		}
		list.remove(group);
		repo.save(lecturer);
		return lecturer.build();
	}

}
