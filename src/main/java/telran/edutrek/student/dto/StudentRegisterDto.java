package telran.edutrek.student.dto;



import java.util.ArrayList;

import lombok.Getter;
import lombok.NoArgsConstructor;
import telran.edutrek.contact.dto.UserContactDto;
import telran.edutrek.utils.Course;
import telran.edutrek.utils.StatusContact;

@Getter
@NoArgsConstructor
public class StudentRegisterDto extends UserContactDto
{
	private String group;
	private int cost_course;
	private Integer[] status_payment;
	public StudentRegisterDto(String id, String name, String surName, String phone, String email, String city,
			Course course, ArrayList<String> sourse, String comment, StatusContact statusContact, String group,
			int cost_course) {
		super(id, name, surName, phone, email, city, course, sourse, comment, statusContact);
		this.group = group;
		this.cost_course = cost_course;
		this.status_payment = new Integer[] {0, cost_course};
	}
	
	
}
