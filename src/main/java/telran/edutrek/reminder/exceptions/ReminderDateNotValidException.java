package telran.edutrek.reminder.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ReminderDateNotValidException extends RuntimeException
{


		private static final long serialVersionUID = -3527730242182555353L;

		public ReminderDateNotValidException(LocalDateTime date) 
		{
			super("Date "+ date.toString() + " is not valid");
		}
}
