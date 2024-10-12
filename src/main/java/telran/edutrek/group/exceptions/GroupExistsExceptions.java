package telran.edutrek.group.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.CONFLICT)
public class GroupExistsExceptions extends RuntimeException
{
	public GroupExistsExceptions(String group)
	{
		super(group);
	}
}
