package telran.edutrek.contact.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.edutrek.contact.dto.UserContactDto;

public interface ContactRepository extends MongoRepository<UserContactDto, String>{

}
