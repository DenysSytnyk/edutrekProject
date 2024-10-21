package telran.edutrek.lecturers.dto;

import java.util.List;

import telran.edutrek.utils.Course;
import telran.edutrek.utils.StatusContact;

public class LecturersUpdateDto {

	private String name;
	private String surName;
	private String phone;
	private String email;
	private String city;
	private Course course;
	private StatusContact statusContact;
	private List<String> group;
	
}
