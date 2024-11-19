package telran.edutrek.tools.status.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.CONFLICT)
public class StatusExistsException extends RuntimeException
{
	public StatusExistsException(String status)
	{
		super(status);
	}
}
