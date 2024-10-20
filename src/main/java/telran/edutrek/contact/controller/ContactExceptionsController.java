package telran.edutrek.contact.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import telran.edutrek.contact.exceptions.UserContactExistsException;
import telran.edutrek.contact.exceptions.UserContactNotFoundException;
import static telran.edutrek.api.AccountingExceptionsMessages.*;

@ControllerAdvice
@Slf4j
public class ContactExceptionsController 
{
	
	@ExceptionHandler(UserContactExistsException.class)
	ResponseEntity<String> handlerUserContactExistException(UserContactExistsException e)
	{
		log.error(e.getMessage());
		String message = USER_EXIST + e.getMessage();
		return new ResponseEntity<String>(message, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UserContactNotFoundException.class)
	ResponseEntity<String> handlerUserContactNotFoundException(UserContactNotFoundException e)
	{
		log.error(e.getMessage());
		String message = USER_NOT_FOUND + e.getMessage();
		return new ResponseEntity<String>(message, HttpStatus.NOT_FOUND);
	}
	
	
}