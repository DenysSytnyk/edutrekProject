package telran.edutrek.reminder.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ReminderCommentNotValidException extends RuntimeException
{


		private static final long serialVersionUID = -3527730242182555353L;

		public ReminderCommentNotValidException() 
		{
			super("Comment format is not valid");
		}
}
