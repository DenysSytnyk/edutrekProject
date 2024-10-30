package telran.edutrek.lecturers.dto;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import telran.edutrek.contact.dto.UserContactDto;
import telran.edutrek.student.dto.GroupForStudentDto;
import telran.edutrek.utils.Course;
import telran.edutrek.utils.StatusContact;

@Getter
@NoArgsConstructor
public class LecturersDto extends UserContactDto{

	private List<GroupForStudentDto> group;

	public LecturersDto(String id, String name, String surName, String phone, String email, String city, Course course,
			ArrayList<String> sourse, String comment, StatusContact statusContact,
			List<GroupForStudentDto> group, LinkedList<String> logs) {
		super(id, name, surName, phone, email, city, course, sourse, comment, statusContact, logs);
		this.group = group;
	}
	



	
}
