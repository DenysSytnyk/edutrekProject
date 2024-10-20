package telran.edutrek.group.entities;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import telran.edutrek.group.dto.GroupDto;
import telran.edutrek.group.dto.StudentForGroupDto;
import telran.edutrek.reminder.dto.ReminderDto;
import telran.edutrek.utils.GroupDays;

@Getter
@Setter
@Document(collection = "edutrek_groups")
public class GroupData 
{
	
	@Setter(value = AccessLevel.NONE)
	private String id;
	private String name;
	private String whatsapp;
	private String skype;
	private String slack;
	private LocalDate startDate;
	private LocalDate endDate;
	private GroupDays[] lessons;
	private GroupDays[] webinars;
	private ReminderDto reminder;
	private boolean deactivate;
	private boolean status;
	private List<StudentForGroupDto> students;
	
	
	public GroupData(String name, String whatsapp, String skype, String slack, LocalDate startDate,
			LocalDate endDate, GroupDays[] lessons, GroupDays[] webinars, ReminderDto reminder, boolean status, boolean deactivate,
			List<StudentForGroupDto> students) {
		super();
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
