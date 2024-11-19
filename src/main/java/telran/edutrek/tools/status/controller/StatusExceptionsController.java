package telran.edutrek.tools.status.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import telran.edutrek.tools.status.exceptions.InvalidStatusNameException;
import telran.edutrek.tools.status.exceptions.StatusExistsException;
import telran.edutrek.tools.status.exceptions.StatusNotFoundException;

@ControllerAdvice
public class StatusExceptionsController 
{

	@ExceptionHandler(StatusExistsException.class)
	ResponseEntity<String> handlerStatusExistsException(StatusExistsException e)
	{
		String message = "Status exists: " + e.getMessage();
		return new ResponseEntity<String>(message, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(StatusNotFoundException.class)
	ResponseEntity<String> handlerStatusNotFoundException(StatusNotFoundException e)
	{
		String message = "Status not found: " + e.getMessage();
		return new ResponseEntity<String>(message, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidStatusNameException.class)
	ResponseEntity<String> handlerInvalidStatusNameException(InvalidStatusNameException e)
	{
		String message = "Status name is invalid: " + e.getMessage();
		return new ResponseEntity<String>(message, HttpStatus.CONFLICT);
	}
}
