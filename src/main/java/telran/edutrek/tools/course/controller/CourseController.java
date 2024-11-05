package telran.edutrek.tools.course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import telran.edutrek.tools.course.dto.CourseDto;
import telran.edutrek.tools.course.service.ICourseManagement;

@RequestMapping("/course")
@RestController
public class CourseController
{
	@Autowired
	ICourseManagement service;

	@PostMapping("/add/{name}")
	public CourseDto addCourse(@PathVariable String name) 
	{
		return service.addCourse(name);
	}

	@GetMapping("/all")
	public List<CourseDto> getAllCourses() 
	{
		return service.getAllCourses();
	}

	@GetMapping("/id/{id}")
	public CourseDto getCourseById(@PathVariable String id) 
	{
		return service.getCourseById(id);
	}

	@PutMapping("/update/{id}/{newName}")
	public CourseDto updateCourseById(@PathVariable String id,@PathVariable String newName) 
	{
		return service.updateCourseById(id, newName);
	}

	@DeleteMapping("/del/{id}")
	public CourseDto deleteCourseById(@PathVariable String id) 
	{
		return service.deleteCourseById(id);
	}

}
