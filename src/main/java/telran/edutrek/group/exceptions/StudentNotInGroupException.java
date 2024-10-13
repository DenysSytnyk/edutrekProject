package telran.edutrek.group.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class StudentNotInGroupException extends RuntimeException
{
	public StudentNotInGroupException(String group, String student)
	{
		super(student + "not in " + group);
	}
}