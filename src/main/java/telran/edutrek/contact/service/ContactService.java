package telran.edutrek.contact.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import telran.edutrek.contact.dto.ContactUpdateDto;
import telran.edutrek.contact.dto.UserContactDto;
import telran.edutrek.contact.dto.UserContactRegisterDto;
import telran.edutrek.contact.entities.UserContact;
import telran.edutrek.contact.exceptions.UserContactExistsException;
import telran.edutrek.contact.exceptions.UserContactNotFoundException;
import telran.edutrek.contact.repo.ContactRepository;

public class ContactService implements IContactManagement{
	
	@Autowired
	ContactRepository repo;

	@Override
	public UserContactDto addNewContact(UserContactRegisterDto user) {
		if(repo.existsById(user.getId()))
			throw new UserContactExistsException(user.getId());
		UserContact us=new UserContact(user.getId(), user.getName(), user.getSurName(),
				user.getPhone(), user.getEmail(), user.getCity(), user.getCourse(), user.getSourse(), user.getComment(), user.getStatusContact());
		repo.save(us);
		return new UserContactDto(user.getId(), user.getName(), user.getSurName(), 
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
		List<UserContactDto> res=repo.findAll().stream().map(u->u.build()).collect(Collectors.toList());
		return res;
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