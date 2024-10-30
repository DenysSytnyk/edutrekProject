package telran.edutrek.group.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import telran.edutrek.group.exceptions.GroupDeactivatedException;
import telran.edutrek.group.exceptions.GroupExistsExceptions;
import telran.edutrek.group.exceptions.GroupNotFoundExceptions;
import telran.edutrek.group.exceptions.StudentExistsInGroupExceptions;
import telran.edutrek.group.exceptions.StudentNotInGroupException;

import static telran.edutrek.api.GroupExceptionMessages.*;

@ControllerAdvice
public class GroupExceptionsController 
{
	@ExceptionHandler(GroupExistsExceptions.class)
	ResponseEntity<String> handlerGroupExistsException(GroupExistsExceptions e)
	{
		String message = GROUP_EXISTS + e.getMessage();
		return new ResponseEntity<String>(message, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(GroupNotFoundExceptions.class)
	ResponseEntity<String> handlerGroupNotFoundException(GroupNotFoundExceptions e)
	{
		String message = GROUP_NOT_FOUND + e.getMessage();
		return new ResponseEntity<String>(message, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(StudentExistsInGroupExceptions.class)
	ResponseEntity<String> handlerStudentExistsException(StudentExistsInGroupExceptions e)
	{
		String message = STUDENT_EXISTS_IN_GROUP + e.getMessage();
		return new ResponseEntity<String>(message, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(StudentNotInGroupException.class)
	ResponseEntity<String> handlerStudentNotInGroupException(StudentNotInGroupException e)
	{
		String message = STUDENT_NOT_IN_GROUP + e.getMessage();
		return new ResponseEntity<String>(message, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(GroupDeactivatedException.class)
	ResponseEntity<String> handlerGroupDeactivatedException(GroupDeactivatedException e)
	{
		String message = GROUP_DEACTIVATED + e.getMessage();
		return new ResponseEntity<String>(message, HttpStatus.CONFLICT);
	}
}
