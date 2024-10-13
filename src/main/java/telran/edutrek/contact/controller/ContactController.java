package telran.edutrek.contact.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import telran.edutrek.contact.dto.ContactUpdateDto;
import telran.edutrek.contact.dto.UserContactDto;
import telran.edutrek.contact.dto.UserContactRegisterDto;
import telran.edutrek.contact.entities.UserContact;
import telran.edutrek.contact.service.IContactManagement;

@RequestMapping("/contact")
@RestController
public class ContactController {
	@Autowired
	IContactManagement server;

	@PostMapping("/add")
	public UserContactDto addNewContact(@RequestBody UserContactRegisterDto user) {
		return server.addNewContact(user);
	}

	@DeleteMapping("/{id}")
	public UserContactDto removeContactById(@PathVariable String id) {
		
		return server.removeContactById(id);
	}

	@GetMapping("/id/{id}")
	public UserContact getContactById(@PathVariable String id) {
		
		return server.getContactById(id);
	}

	@PutMapping("/update")
	public UserContactDto updateContactById(@RequestBody ContactUpdateDto newContact) {
		
		return server.updateContactById(newContact);
	}

	@GetMapping("/all")
	public List<UserContactDto> getAllContact() {
		
		return server.getAllContact();
	}

	@GetMapping("/all/{name}")
	public List<UserContactDto> getContactByName(@PathVariable String name) {
		
		return server.getContactBySurName(name);
	}

	@GetMapping("/all/{sur_name}")
	public List<UserContactDto> getContactBySurName(@PathVariable String surName) {
		
		return server.getContactBySurName(surName);
	}

}
