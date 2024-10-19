package telran.edutrek.accounting.controller;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import lombok.extern.slf4j.Slf4j;
import telran.edutrek.accounting.exceptions.LoginNotValidException;
import telran.edutrek.accounting.exceptions.PasswordNotValidException;
import telran.edutrek.accounting.exceptions.UserExistsException;
import telran.edutrek.accounting.exceptions.UserIsBlockedException;
import telran.edutrek.accounting.exceptions.UserNotFoundException;


import static telran.edutrek.api.AccountingExceptionsMessages.*;

@ControllerAdvice
@Slf4j
public class AccountingExceptionsController 
{

	@ExceptionHandler(MethodArgumentNotValidException.class)
	ResponseEntity<String> methodArgumentNotValidException(MethodArgumentNotValidException e)
	{
		log.error(e.getMessage());
		String message = e.getAllErrors().stream().map(er -> er.getDefaultMessage())
				.collect(Collectors.joining("; "));
		return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(HandlerMethodValidationException.class)
	ResponseEntity<String> handlerMethodValidationException(HandlerMethodValidationException e)
	{
		log.error(e.getMessage());
		String message = e.getAllErrors().stream().map(er -> er.getDefaultMessage())
				.collect(Collectors.joining("; "));
		return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(PasswordNotValidException.class)
	ResponseEntity<String> handlerPasswordValidationException(PasswordNotValidException e)
	{
		log.error(e.getMessage());
		String message = PASSWORD_NOT_VALID + e.getMessage();
		return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(LoginNotValidException.class)
	ResponseEntity<String> handlerLoginValidationException(LoginNotValidException e)
	{
		log.error(e.getMessage());
		String message = LOGIN_NOT_VALID + e.getMessage();
		return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserExistsException.class)
	ResponseEntity<String> handlerUserExistException(UserExistsException e)
	{
		log.error(e.getMessage());
		String message = USER_EXIST + e.getMessage();
		return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UserNotFoundException.class)
	ResponseEntity<String> handlerUserNotFoundException(UserNotFoundException e)
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
