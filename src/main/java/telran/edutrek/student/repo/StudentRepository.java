package telran.edutrek.student.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.edutrek.student.dto.StudentDto;

public interface StudentRepository extends MongoRepository<StudentDto, String>
{

}
