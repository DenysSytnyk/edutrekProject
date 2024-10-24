package telran.edutrek.lecturers.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.CONFLICT)
public class GroupToLecturerNotExistsException extends RuntimeException {

	public GroupToLecturerNotExistsException(String message) {
		super("Group "+message+" not exists");
		
	}

}
