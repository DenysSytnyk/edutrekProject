package telran.edutrek.lecturers.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.CONFLICT)
public class GroupToLecturerExistsException extends RuntimeException {

	public GroupToLecturerExistsException(String message) {
		super("Group "+message+" exists");
		
	}

	
}
