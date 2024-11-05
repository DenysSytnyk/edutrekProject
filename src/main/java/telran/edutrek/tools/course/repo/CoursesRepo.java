package telran.edutrek.tools.course.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.edutrek.tools.course.entities.CourseData;

public interface CoursesRepo extends MongoRepository<CourseData, String>
{

	Optional<CourseData> findByName(String name);

}
