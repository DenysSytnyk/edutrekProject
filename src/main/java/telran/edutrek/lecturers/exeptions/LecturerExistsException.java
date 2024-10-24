package telran.edutrek.lecturers.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.CONFLICT)
public class LecturerExistsException extends RuntimeException {

	public LecturerExistsException (String id) {
		super("Lecturer "+id + " exists");
	}
}
