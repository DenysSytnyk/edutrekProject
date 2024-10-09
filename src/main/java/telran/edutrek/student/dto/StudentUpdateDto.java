package telran.edutrek.student.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import telran.edutrek.utils.Course;
import telran.edutrek.utils.StatusContact;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class StudentUpdateDto 
{
	private String name;
	private String surName;
	private String phone;
	private String email;
	private String city;
	private Course course;
	private StatusContact statusContact;
	private String group;
	private int cost_course;
}
