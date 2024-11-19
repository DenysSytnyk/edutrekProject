package telran.edutrek.tools.status.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.CONFLICT)
public class InvalidStatusNameException extends RuntimeException
{
	public InvalidStatusNameException(String status)
	{
		super(status);
	}
}
