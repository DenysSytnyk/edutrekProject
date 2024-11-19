package telran.edutrek.tools.status.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class StatusNotFoundException extends RuntimeException
{
	public StatusNotFoundException(String status)
	{
		super(status);
	}
}
