package telran.edutrek.contact.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.edutrek.contact.dto.ContactUpdateDto;
import telran.edutrek.contact.dto.UserContactDto;
import telran.edutrek.contact.dto.UserContactRegisterDto;
import telran.edutrek.contact.entities.UserContact;
import telran.edutrek.contact.exceptions.UserContactNotFoundException;
import telran.edutrek.contact.repo.ContactRepository;
import telran.edutrek.student.dto.StudentDto;
import telran.edutrek.student.repo.StudentRepository;

@Service
public class ContactService implements IContactManagement{
	
	@Autowired
	ContactRepository repo;
	
	@Autowired
	StudentRepository studRepo;

	@Override
	public UserContactDto addNewContact(UserContactRegisterDto user) {

		UserContact us=new UserContact(user.getName(), user.getSurName(),
				user.getPhone(), user.getEmail(), user.getCity(), user.getCourse(), user.getSourse(), user.getComment(), user.getStatusContact());
		us = repo.save(us);
		return new UserContactDto(us.getId(), user.getName(), user.getSurName(), 
				user.getPhone(), user.getEmail(), user.getCity(), user.getCourse(), user.getSourse(),
				user.getComment(), user.getStatusContact());
	}

	@Override
	public UserContactDto removeContactById(String id) {
		UserContact user=getContactById(id);
		repo.delete(user);
		return user.build();
	}

	@Override
	public UserContact getContactById(String id) {
		
		return repo.findById(id).orElseThrow(()->
		new UserContactNotFoundException(id)
				);
	}

	@Override
	public UserContactDto updateContactById(ContactUpdateDto newContact) {
		if(!repo.existsById(newContact.getId()))
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
		repo.save(user);
		return user.build();
	}

	@Override
	public List<UserContactDto> getAllContact() {
		try {
			List<UserContactDto> res = new ArrayList<>();
			Collection<UserContactDto> resCont=repo.findAll().stream().map(u->u.build()).collect(Collectors.toList());
			Collection<StudentDto> resStud=studRepo.findAll().stream().map(u->u.build()).collect(Collectors.toList());
			
			res.addAll(resStud);
			res.addAll(resCont);
			return res;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		
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
