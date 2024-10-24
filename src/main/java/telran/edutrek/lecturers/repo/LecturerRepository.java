package telran.edutrek.lecturers.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.edutrek.lecturers.entities.LecturersContact;

public interface LecturerRepository extends MongoRepository<LecturersContact, String>{

}
