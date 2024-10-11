package telran.edutrek.reminder.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReminderDto 
{
	LocalDateTime date;
	String comment;
}
