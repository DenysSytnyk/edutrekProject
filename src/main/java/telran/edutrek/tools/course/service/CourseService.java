package telran.edutrek.tools.course.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.edutrek.tools.course.dto.CourseDto;
import telran.edutrek.tools.course.entities.CourseData;
import telran.edutrek.tools.course.repo.CoursesRepo;

@Service
public class CourseService implements ICourseManagement
{
	@Autowired
	CoursesRepo repo;

	@Override
	public CourseDto addCourse(String name) 
	{
		return repo.save(new CourseData(name)).build();
	}

	@Override
	public List<CourseDto> getAllCourses() 
	{
		return repo.findAll().stream().map(cd -> cd.build()).toList();
	}

	@Override
	public CourseDto getCourseById(String id) 
	{
		return repo.findById(id).orElse(null).build();
	}

	@Override
	public CourseDto updateCourseById(String id, String newName) 
	{
		CourseData course = repo.findById(id).orElse(null);
		course.setName(newName);
		return repo.save(course).build();
	}

	@Override
	public CourseDto deleteCourseById(String id) 
	{
		CourseData course = repo.findById(id).orElse(null);
		repo.deleteById(id);
		return course.build();
	}

}
