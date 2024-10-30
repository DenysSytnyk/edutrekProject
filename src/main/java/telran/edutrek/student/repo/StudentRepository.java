package telran.edutrek.student.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.edutrek.student.entities.StudentContact;

public interface StudentRepository extends MongoRepository<StudentContact, String> {
	Optional<StudentContact> findByPhone(String phone);
}


