package telran.edutrek.lecturers.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import telran.edutrek.student.dto.GroupForStudentDto;
import telran.edutrek.utils.Course;
import telran.edutrek.utils.StatusContact;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class LecturersUpdateDto {
	
	private String name;
	private String surName;
	private String phone;
	private String email;
	private String city;
	private Course course;
	private StatusContact statusContact;
	private List<GroupForStudentDto> group;
	
}
