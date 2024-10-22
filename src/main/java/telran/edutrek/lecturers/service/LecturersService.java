package telran.edutrek.lecturers.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.edutrek.contact.entities.UserContact;
import telran.edutrek.contact.exceptions.UserContactExistsException;
import telran.edutrek.contact.exceptions.UserContactNotFoundException;
import telran.edutrek.group.dto.StudentForGroupDto;
import telran.edutrek.lecturers.dto.LecturersDto;
import telran.edutrek.lecturers.dto.LecturersRegisterDto;
import telran.edutrek.lecturers.dto.LecturersUpdateDto;
import telran.edutrek.lecturers.entities.LecturersContact;
import telran.edutrek.lecturers.repo.LecturerRepository;
import telran.edutrek.student.dto.GroupForStudentDto;

@Service
public class LecturersService implements ILecturersManagement{
	
	@Autowired
	LecturerRepository repo;
	

	@Override
	public LecturersDto createLecturer(LecturersRegisterDto lecturer) {
		if(repo.existsById(lecturer.getPhone()))
			throw new LecturerExistsException(lecturer.getPhone());
		LecturersContact lecturerContact=new LecturersContact(lecturerContact.getId(), lecturerContact.getName(), lecturerContact.getSurName(), lecturerContact.getPhone(),
				lecturerContact.getEmail(), lecturerContact.getCity(), null, lecturerContact.getSourse(), lecturerContact.getComment(),lecturerContact.getStatusContact(), lecturerContact.getGroup());
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
	public LecturersDto updateLecturer(LecturersUpdateDto  newLecturer) {
		if(!repo.existsById(newLecturer.get))
			throw new UserContactNotFoundException(newLecturer.getId());
		UserContact user=getContactById(newLecturer.getId());
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
		if(newLecturer.getCourse()==null) {
			user.setCourse(user.getCourse());
		}
		if(newLecturer.getSourse()==null) {
			user.setSourse(user.getSourse());
		}
		if(newLecturer.getComment()==null) {
			user.setComment(user.getComment());
		}
		if(newLecturer.getStatusContact()==null) {
			user.setStatusContact(user.getStatusContact());
		}
		user.setName(newLecturer.getName());
		user.setPhone(newLecturer.getPhone());
		user.setEmail(newLecturer.getEmail());
		user.setCity(newLecturer.getCity());
		user.setCourse(newLecturer.getCourse());
		user.setSourse(newLecturer.getSourse());
		user.setComment(newLecturer.getComment());
		user.setStatusContact(newLecturer.getStatusContact());
		repo.save(user);
		return user.build();
	}

	@Override
	public LecturersDto getLecturerById(String id) {
		return getLecturer(id).build();
		
	}
	
	public LecturersContact getLecturer(String id) {
		return repo.findById(id).orElseThrow(
				new LecturerNotFoundException(id));
		
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LecturersDto removeGroupToLecturer(String id, GroupForStudentDto group) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
