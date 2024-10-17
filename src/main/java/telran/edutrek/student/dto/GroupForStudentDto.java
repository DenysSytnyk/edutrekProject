package telran.edutrek.student.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import telran.edutrek.group.entities.GroupData;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GroupForStudentDto 
{
	String name;
	String id;
	
	public static GroupForStudentDto toGroup(GroupData group)
	{
		return new GroupForStudentDto(group.getName(), group.getId());
	}
}
