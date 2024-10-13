package telran.edutrek.group.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.CONFLICT)
public class StudentExistsInGroupExceptions extends RuntimeException
{
	public StudentExistsInGroupExceptions(String group, String student)
	{
		super(student + " in " + group);
	}
}
