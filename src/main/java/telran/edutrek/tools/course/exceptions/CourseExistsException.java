package telran.edutrek.tools.course.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.CONFLICT)
public class CourseExistsException extends RuntimeException
{
	public CourseExistsException(String course) 
	{
		super(course);
	}
}
