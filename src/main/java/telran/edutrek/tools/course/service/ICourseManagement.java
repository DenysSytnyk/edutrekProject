package telran.edutrek.tools.course.service;

import java.util.List;

import telran.edutrek.tools.course.dto.CourseDto;

public interface ICourseManagement 
{
	CourseDto addCourse(String name);
	List<CourseDto> getAllCourses();
	CourseDto getCourseById(String id);
	CourseDto updateCourseById(String id, String newName);
	CourseDto deleteCourseById(String id);
}
