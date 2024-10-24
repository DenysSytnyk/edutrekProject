package telran.edutrek.group.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import telran.edutrek.student.entities.StudentContact;

@AllArgsConstructor
@Getter
@Setter
public class StudentForGroupDto 
{
	String name;
	String surName;
	String id;
	
	public static StudentForGroupDto toStudent(StudentContact student)
	{
		return new StudentForGroupDto(student.getName(), student.getSurName(), student.getId());
	}
}
