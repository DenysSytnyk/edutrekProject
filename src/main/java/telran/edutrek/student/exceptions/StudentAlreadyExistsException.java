package telran.edutrek.student.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class StudentAlreadyExistsException extends RuntimeException {

	public StudentAlreadyExistsException(String id) {
		super("Student " + id + " already exists");
	}
}
