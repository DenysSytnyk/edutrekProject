package telran.edutrek.student.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class StudentNotFoundException extends RuntimeException
{

	public StudentNotFoundException(String login)
	{
		super("Student " + login + " not found");
	}
}
