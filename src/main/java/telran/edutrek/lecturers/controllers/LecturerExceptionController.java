package telran.edutrek.lecturers.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import static telran.edutrek.api.LecturerExceptionMessages.*;
import telran.edutrek.lecturers.exeptions.GroupToLecturerExistsException;
import telran.edutrek.lecturers.exeptions.GroupToLecturerNotExistsException;
import telran.edutrek.lecturers.exeptions.LecturerExistsException;
import telran.edutrek.lecturers.exeptions.LecturerNotFoundException;

@ControllerAdvice
public class LecturerExceptionController {

	@ExceptionHandler(GroupToLecturerExistsException.class)
	ResponseEntity<String> handlerGroupToLecturerExistsException(GroupToLecturerExistsException e){
		String massage= GROUP_TO_LECTURER_EXISTS+e.getMessage();
		return new ResponseEntity<String>(massage,HttpStatus.CONFLICT);
		
	}
	@ExceptionHandler(GroupToLecturerNotExistsException.class)
	ResponseEntity<String> handlerGroupToLecturerNotExistsException(GroupToLecturerNotExistsException e){
		String massage= GROUP_TO_LECTURER_NOT_EXISTS+e.getMessage();
		return new ResponseEntity<String>(massage,HttpStatus.CONFLICT);
		
	}
	@ExceptionHandler(LecturerExistsException.class)
	ResponseEntity<String> handlerLecturerExistsException(LecturerExistsException e){
		String massage= LECTURER_EXISTS+e.getMessage();
		return new ResponseEntity<String>(massage,HttpStatus.CONFLICT);
		
	}
	@ExceptionHandler(LecturerNotFoundException.class)
	ResponseEntity<String> handlerLecturerNotFoundException(LecturerNotFoundException e){
		String massage= LECTURER_NOT_FOUND+e.getMessage();
		return new ResponseEntity<String>(massage,HttpStatus.NOT_FOUND);
		
	}

}
