package telran.edutrek.contact.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserContactNotFoundException extends RuntimeException{

	public UserContactNotFoundException(String id)
	{
		super("Contact " + id + " not found");
	}
}
