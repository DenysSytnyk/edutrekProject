package telran.edutrek.contact.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserContactExistsException extends RuntimeException {

	public UserContactExistsException (String id) {
		super("Contact "+ id + " exists");
	}
}
