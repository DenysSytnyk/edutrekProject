package telran.edutrek.contact.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.edutrek.contact.entities.UserContact;


public interface ContactRepository extends MongoRepository<UserContact, String>{

	

}
