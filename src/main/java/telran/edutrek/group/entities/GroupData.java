package telran.edutrek.group.entities;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import telran.edutrek.group.dto.GroupDto;
import telran.edutrek.reminder.dto.ReminderDto;
import telran.edutrek.student.dto.StudentDto;
import telran.edutrek.utils.GroupDays;

@Getter
@Setter
@Document(collection = "edutrek_groups")
public class GroupData 
{
	@Id
	@Setter(value = AccessLevel.NONE)
	private String id;
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
	private boolean status;
	private List<StudentDto> students;
	
	
	public GroupData(String id, String name, String whatsapp, String skype, String slack, LocalDate startDate,
			LocalDate endDate, GroupDays lessons, GroupDays webinars, ReminderDto reminder, boolean status, boolean deactivate,
			List<StudentDto> students) {
		super();
		this.id = name;
		this.name = name;
		this.whatsapp = whatsapp;
		this.skype = skype;
		this.slack = slack;
		this.startDate = startDate;
		this.endDate = endDate;
		this.lessons = lessons;
		this.webinars = webinars;
		this.reminder = reminder;
		this.deactivate = deactivate;
		this.status = status;
		this.students = students;
	}
	
	public GroupDto build()
	{
		return new GroupDto(id, name, whatsapp, skype, slack,
				startDate, endDate, lessons, webinars, reminder,
				deactivate, status, students);
	}
}
