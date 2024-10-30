package telran.edutrek.group.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.CONFLICT)
public class GroupDeactivatedException extends RuntimeException
{
	public GroupDeactivatedException(String group)
	{
		super(group);
	}
}
