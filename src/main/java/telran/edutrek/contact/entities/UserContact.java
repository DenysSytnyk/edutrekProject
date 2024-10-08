package telran.edutrek.contact.entities;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import telran.edutrek.utils.Course;
import telran.edutrek.utils.StatusContact;

@Getter
@Setter
@Document(collection = "edutrek_contact")
public class UserContact {

	@Id
	@Setter(value = AccessLevel.NONE)
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
}
