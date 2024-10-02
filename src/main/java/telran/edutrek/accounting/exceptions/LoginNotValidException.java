package telran.edutrek.accounting.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.CONFLICT)
public class LoginNotValidException extends RuntimeException {

	public LoginNotValidException(String login) {
		super("Login "+login+"login is not valid");
	}
}
