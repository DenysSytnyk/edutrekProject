package telran.edutrek.accounting.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.CONFLICT)
public class NameNotValidException extends RuntimeException {

	public NameNotValidException(String name) {
		super("Name "+name+" is not valid");
		
	}

	
}
