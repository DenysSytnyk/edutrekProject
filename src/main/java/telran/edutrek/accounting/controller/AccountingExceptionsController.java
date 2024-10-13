package telran.edutrek.accounting.controller;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import lombok.extern.slf4j.Slf4j;

import telran.edutrek.accounting.exceptions.UserExistsException;
import telran.edutrek.accounting.exceptions.UserIsBlockedException;
import telran.edutrek.accounting.exceptions.UserNotFoundException;
import telran.edutrek.contact.exceptions.UserContactExistsException;
import telran.edutrek.contact.exceptions.UserContactNotFoundException;

import static telran.edutrek.api.AccountingExceptionsMessages.*;

@ControllerAdvice
@Slf4j
public class AccountingExceptionsController 
{
	private ResponseEntity<String> returnResponse(String message, HttpStatus status)
	{
		log.error(message);
		return new ResponseEntity<String>(message, status);
	}
	
	@ExceptionHandler(UserContactExistsException.class)
	ResponseEntity<String> handlerUsercontactExistException(UserExistsException e)
	{
		log.error(e.getMessage());
		String message = USER_EXIST + e.getMessage();
		return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UserContactNotFoundException.class)
	ResponseEntity<String> handlerUserContactNotFoundException(UserNotFoundException e)
	{
		log.error(e.getMessage());
		String message = USER_NOT_FOUND + e.getMessage();
		return new ResponseEntity<String>(message, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UsernameNotFoundException.class)
	ResponseEntity<String> handlerUserNameNotFoundException(UsernameNotFoundException e)
	{
		log.error(e.getMessage());
		String message = USER_NOT_FOUND + e.getMessage();
		return new ResponseEntity<String>(message, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserIsBlockedException.class)
	ResponseEntity<String> handlerUserBlockedException(UserIsBlockedException e)
	{
		log.error(e.getMessage());
		String message = USER_IS_BLOCKED + e.getMessage();
		return new ResponseEntity<String>(message, HttpStatus.CONFLICT);
	}

}
