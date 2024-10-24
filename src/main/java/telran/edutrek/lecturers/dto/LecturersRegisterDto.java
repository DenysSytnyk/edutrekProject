package telran.edutrek.lecturers.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import telran.edutrek.contact.dto.UserContactRegisterDto;
import telran.edutrek.student.dto.GroupForStudentDto;
import telran.edutrek.utils.Course;
import telran.edutrek.utils.StatusContact;

@Getter
@NoArgsConstructor
public class LecturersRegisterDto extends UserContactRegisterDto{
	private List<GroupForStudentDto> group;

	public LecturersRegisterDto(String id, String name, String surName, String phone, String email, String city, Course course,
			ArrayList<String> sourse, String comment, StatusContact statusContact, List<GroupForStudentDto> group) {
		super(id,name, surName, phone, email, city, course, sourse, comment, statusContact);
		this.group=group;
	}

	

	
	
}
