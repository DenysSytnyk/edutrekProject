package telran.edutrek.group.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class GroupNotFoundExceptions extends RuntimeException
{
	public GroupNotFoundExceptions(String group)
	{
		super(group);
	}
}
