package telran.edutrek.contact.service;

import java.util.List;

import telran.edutrek.contact.dto.UserContactDto;

public interface IContactManagement {
	
	UserContactDto addNewContact(UserContactRegisterDto user);
	UserContactDto removeContactById(String id);
	UserContactDto getContactById(String id);
	List<UserContactDto> getAllContact();
	List<UserContactDto> getContactByName(String name);
	List<UserContactDto> getContactBySurName(String surName);
	

	
}
