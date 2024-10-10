package telran.edutrek.group.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import telran.edutrek.reminder.dto.ReminderDto;
import telran.edutrek.student.dto.StudentDto;
import telran.edutrek.utils.GroupDays;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GroupUpdateDto 
{
	private String name;
	private String whatsapp;
	private String skype;
	private String slack;
	private LocalDate startDate;
	private LocalDate endDate;
	private GroupDays lessons;
	private GroupDays webinars;
	private ReminderDto reminder;
	private boolean deactivate;
	private List<StudentDto> students;
	
}
