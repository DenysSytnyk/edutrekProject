package telran.edutrek.contact.entities;

import java.util.ArrayList;
import java.util.LinkedList;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import telran.edutrek.contact.dto.UserContactDto;
import telran.edutrek.utils.Course;
import telran.edutrek.utils.StatusContact;

@Getter
@Setter
@Document(collection = "edutrek_contact")
public class UserContact {


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
	private LinkedList<String> logs;
	public UserContact(String name, String surName, String phone, String email, String city, Course course,
			ArrayList<String> sourse, String comment, StatusContact statusContact, LinkedList<String> logs) {
		super();
		this.name = name;
		this.surName = surName;
		this.phone = phone;
		this.email = email;
		this.city = city;
		this.course = course;
		this.sourse = new ArrayList<String>();
		this.comment = comment;
		this.statusContact = statusContact;
		this.logs = logs;
	}
	
	public UserContactDto build() {
		return UserContactDto.builder().id(id).name(name).surName(surName)
				.phone(phone).email(email).city(city).course(course)
				.sourse(sourse).comment(comment).statusContact(statusContact).logs(logs).build();
	}
}
