package telran.edutrek.student.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import telran.edutrek.student.entities.StudentContact;

public interface StudentRepository extends MongoRepository<StudentContact, String> {


import telran.edutrek.student.entities.StudentContact;

public interface StudentRepository extends MongoRepository<StudentContact, String>
{

}
