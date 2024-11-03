package telran.edutrek.contact.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.edutrek.contact.dto.UserContactDto;
import telran.edutrek.contact.entities.UserContact;


public interface ContactRepository extends MongoRepository<UserContact, String>{

	List<UserContactDto> findByName(String name);

	List<UserContactDto> findBysurName(String surName);

	

	

	

	

}
