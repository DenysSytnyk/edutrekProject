package telran.edutrek.contact.dto;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import telran.edutrek.contact.entities.UserContact;
import telran.edutrek.utils.Course;
import telran.edutrek.utils.StatusContact;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserContactDto {
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


	static UserContactDto build(UserContact user) {
		return new UserContactDto(user.getId(), user.getName(), user.getSurName(), user.getPhone(),
				user.getEmail(), user.getCity(), user.getCourse(), user.getSourse(), user.getComment(), user.getStatusContact());
	}
}
