package telran.edutrek.contact.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import telran.edutrek.contact.dto.ContactUpdateDto;
import telran.edutrek.contact.dto.UserContactDto;
import telran.edutrek.contact.dto.UserContactRegisterDto;
import telran.edutrek.contact.repo.ContactRepository;

public class ContactService implements IContactManagement{
	
	@Autowired
	ContactRepository repo;

	@Override
	public UserContactDto addNewContact(UserContactRegisterDto user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserContactDto removeContactById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserContactDto getContactById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserContactDto updateContactById(ContactUpdateDto newContact) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserContactDto> getAllContact() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserContactDto> getContactByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserContactDto> getContactBySurName(String surName) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
