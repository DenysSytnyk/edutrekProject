package telran.edutrek.accounting.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserIsBlockedException extends RuntimeException {


	private static final long serialVersionUID = -4473636852403897808L;

	public UserIsBlockedException(String login) {
		super("User "+login + " in block");
	}

}
