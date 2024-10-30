package telran.edutrek.contact.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
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
import telran.edutrek.utils.StatusContact;

@Service
@Slf4j
public class ContactService implements IContactManagement{
	
	@Autowired
	ContactRepository repo;
	
	@Autowired
	StudentRepository studRepo;

	@Override
	public UserContactDto addNewContact(UserContactRegisterDto user) {
		if(repo.existsById(user.getPhone()))
			throw new UserContactExistsException(user.getPhone());
		
		UserContact us= UserContactDto.toUserContact(user);
		
		String createLog = LocalDate.now().toString() + " - Contact created";
		us.getLogs().add(createLog);
		us = repo.save(us);
		
		return us.build();
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
		List<StudentDto> res1=studRepo.findAll().stream().map(u->u.build()).collect(Collectors.toList());
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

	@Override
	public boolean addCommentById(String id, String comment) 
	{
		if (comment==null)
			return false;
		
		UserContact user=getContactById(id);
		user.setComment(comment);
		
		String createLog = LocalDate.now().toString() + comment;
		user.getLogs().add(createLog);
		repo.save(user);
		return true;
	}

	@Override
	public List<UserContactDto> getAllActiveContact() 
	{
		List<UserContactDto> res=repo.findAll().stream()
				.filter((u) ->
				u.getStatusContact().equals(StatusContact.IN_WORK) || 
				u.getStatusContact().equals(StatusContact.SAVE_FOR_LATER) || 
				u.getStatusContact().equals(StatusContact.CONSULTATION) ||
				u.getStatusContact().equals(StatusContact.LEAD))
				.map(u->u.build()).collect(Collectors.toList());
		return res;
	}

	
}
