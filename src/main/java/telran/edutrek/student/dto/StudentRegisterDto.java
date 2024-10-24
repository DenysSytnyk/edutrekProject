package telran.edutrek.student.dto;

import java.util.ArrayList;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import telran.edutrek.contact.dto.UserContactRegisterDto;
import telran.edutrek.utils.Course;
import telran.edutrek.utils.StatusContact;

@Getter
@NoArgsConstructor
public class StudentRegisterDto extends UserContactRegisterDto
{
	private List<GroupForStudentDto> group;
	private int cost_course;
	private Integer[] status_payment;
	public StudentRegisterDto(String name, String surName, String phone, String email, String city,
			Course course, ArrayList<String> sourse, String comment, StatusContact statusContact, List<GroupForStudentDto> group, int cost_course) 
      {
		super(name, surName, phone, email, city,  course, sourse, comment, statusContact);
		this.group = group;
		this.cost_course = cost_course;
		this.status_payment = new Integer[] {0, cost_course};
	}
	
}
