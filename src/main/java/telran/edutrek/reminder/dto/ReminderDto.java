package telran.edutrek.reminder.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReminderDto 
{
	LocalDate date;
	String comment;
}
