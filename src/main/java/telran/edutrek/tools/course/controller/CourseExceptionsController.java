package telran.edutrek.tools.course.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import telran.edutrek.tools.course.exceptions.CourseExistsException;
import telran.edutrek.tools.course.exceptions.CourseNotFoundException;

@ControllerAdvice
public class CourseExceptionsController 
{
	@ExceptionHandler(CourseExistsException.class)
	ResponseEntity<String> handlerCourseExistsException(CourseExistsException e)
	{
		String message = "Course exists: " + e.getMessage();
		return new ResponseEntity<String>(message, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(CourseNotFoundException.class)
	ResponseEntity<String> handlerCourseNotFoundException(CourseNotFoundException e)
	{
		String message = "Course not found: " + e.getMessage();
		return new ResponseEntity<String>(message, HttpStatus.NOT_FOUND);
	}
}
