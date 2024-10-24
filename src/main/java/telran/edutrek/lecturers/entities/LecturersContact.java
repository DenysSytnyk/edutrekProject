package telran.edutrek.lecturers.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import telran.edutrek.lecturers.dto.LecturersDto;
import telran.edutrek.student.dto.GroupForStudentDto;
import telran.edutrek.utils.Course;
import telran.edutrek.utils.StatusContact;

@Getter
@Setter
@Document(collection = "edutrek_lecturers")
public class LecturersContact {

	
	private String id;
	private String name;
	private String surName;
	private String phone;
	private String email;
	private String city;
	private Course course;
	private ArrayList<String> sourse;
	private String comment;
	private StatusContact statusContact;
	private List<GroupForStudentDto> group;
	public LecturersContact(String id ,String name, String surName, String phone, String email, String city,
			Course course, ArrayList<String> sourse, String comment, StatusContact statusContact,
			List<GroupForStudentDto> group ) {
		super();
		
		this.id = id;
		this.name = name;
		this.surName = surName;
		this.phone = phone;
		this.email = email;
		this.city = city;
		this.course = null;
		this.sourse = sourse;
		this.comment = comment;
		this.statusContact = statusContact;
		this.group = group;
	}
	public LecturersDto build() {
		return new LecturersDto( name, surName, phone, email, city, course, sourse, comment, statusContact, group);
		
	}
	
	
}
