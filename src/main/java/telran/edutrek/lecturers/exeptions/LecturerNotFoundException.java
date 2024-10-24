package telran.edutrek.lecturers.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class LecturerNotFoundException extends RuntimeException{

	public LecturerNotFoundException(String id) {
		super("Lecturer "+id+" not found");
		
	}

	
}
