package telran.edutrek.contact.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import telran.edutrek.contact.dto.ContactUpdateDto;
import telran.edutrek.contact.dto.UserContactDto;
import telran.edutrek.contact.dto.UserContactRegisterDto;
import telran.edutrek.contact.entities.UserContact;
import telran.edutrek.contact.exceptions.UserContactExistsException;
import telran.edutrek.contact.exceptions.UserContactNotFoundException;
import telran.edutrek.contact.repo.ContactRepository;
import telran.edutrek.student.dto.StudentDto;
import telran.edutrek.student.repo.StudentRepository;

@Service
@Slf4j
public class ContactService implements IContactManagement{
	
	@Autowired
	ContactRepository repoContact;
	@Autowired
	StudentRepository repoStudent;

	@Override
	public UserContactDto addNewContact(UserContactRegisterDto user) {
		
			if(repoContact.existsById(user.getId()))
				
				throw new UserContactExistsException(user.getId());
				
			UserContact us=new UserContact(user.getId(), user.getName(), user.getSurName(),
					user.getPhone(), user.getEmail(), user.getCity(), user.getCourse(), user.getSourse(), user.getComment(), user.getStatusContact());
			repoContact.save(us);
			
		
		return new UserContactDto(user.getId(), user.getName(), user.getSurName(), 
					user.getPhone(), user.getEmail(), user.getCity(), user.getCourse(), user.getSourse(),
					user.getComment(), user.getStatusContact());
	}

	@Override
	public UserContactDto removeContactById(String id) {
		UserContact user=getContactById(id);
		repoContact.delete(user);
		return user.build();
	}

	@Override
	public UserContact getContactById(String id) {
		
		return repoContact.findById(id).orElseThrow(()->
		new UserContactNotFoundException(id)
				);
	}

	@Override
	public UserContactDto updateContactById(ContactUpdateDto newContact) {
		
			if(!repoContact.existsById(newContact.getId()))
				throw new UserContactNotFoundException(newContact.getId());
			UserContact user=getContactById(newContact.getId());
			if(newContact.getName()==null) {
				user.setName(user.getName());
			}
			if(newContact.getSurName()==null) {
				user.setSurName(user.getSurName());
			}
			if(newContact.getPhone()==null) {
				user.setPhone(user.getPhone());
			}
			if(newContact.getEmail()==null) {
				user.setEmail(user.getEmail());
			}
			if(newContact.getCity()==null) {
				user.setCity(user.getCity());
			}
			if(newContact.getCourse()==null) {
				user.setCourse(user.getCourse());
			}
			if(newContact.getSourse()==null) {
				user.setSourse(user.getSourse());
			}
			if(newContact.getComment()==null) {
				user.setComment(user.getComment());
			}
			if(newContact.getStatusContact()==null) {
				user.setStatusContact(user.getStatusContact());
			}
			user.setName(newContact.getName());
			user.setPhone(newContact.getPhone());
			user.setEmail(newContact.getEmail());
			user.setCity(newContact.getCity());
			user.setCourse(newContact.getCourse());
			user.setSourse(newContact.getSourse());
			user.setComment(newContact.getComment());
			user.setStatusContact(newContact.getStatusContact());
			repoContact.save(user);
			return user.build();
		
	}

	@Override
	public List<UserContactDto> getAllContact() {
		List<UserContactDto> res=repoContact.findAll().stream().map(u->u.build()).collect(Collectors.toList());
		List<StudentDto> res1=repoStudent.findAll().stream().map(u->u.build()).collect(Collectors.toList());
		List<UserContactDto> resultat = new ArrayList<UserContactDto>();
		resultat.addAll(res);
		resultat.addAll(res1);	
		return resultat;
	}

	@Override
	public List<UserContactDto> getContactByName(String name) {
	return 	getAllContact().stream().filter(u->u.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
		
	}

	@Override
	public List<UserContactDto> getContactBySurName(String surName) {
		return 	getAllContact().stream().filter(u->u.getSurName().equalsIgnoreCase(surName)).collect(Collectors.toList());
		
	}

	
}
