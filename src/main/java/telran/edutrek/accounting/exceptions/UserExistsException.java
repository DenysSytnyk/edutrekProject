package telran.edutrek.accounting.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserExistsException extends RuntimeException {

	private static final long serialVersionUID = -2959715249076540478L;

	public UserExistsException(String login) {
		super("User "+login + " exists");
	}

}
