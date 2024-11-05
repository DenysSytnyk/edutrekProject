package telran.edutrek.tools.course.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.edutrek.tools.course.entities.CourseData;

public interface CoursesRepo extends MongoRepository<CourseData, String>
{

}
