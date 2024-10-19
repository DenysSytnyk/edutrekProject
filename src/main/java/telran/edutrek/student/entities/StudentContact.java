package telran.edutrek.student.entities;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import telran.edutrek.group.dto.GroupDto;
import telran.edutrek.group.dto.StudentForGroupDto;
import telran.edutrek.reminder.dto.ReminderDto;
import telran.edutrek.student.dto.GroupForStudentDto;
import telran.edutrek.student.dto.PaymentDto;
import telran.edutrek.student.dto.StudentDto;
import telran.edutrek.utils.Course;
import telran.edutrek.utils.StatusContact;


@Getter
@Setter
@Document(collection = "edutrek_students")
public class StudentContact 
{
	
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
	private List<GroupForStudentDto> group;
	private int cost_course;
	private List<PaymentDto> payments;
	private LinkedList<String> logs;
	private Integer[] status_payment;
	private ReminderDto reminder;
	
	
	
	public StudentDto build()
	{
		return new StudentDto(id, name, surName, phone, email, city,
				course, sourse, comment, statusContact, group, cost_course,
				payments, logs, status_payment, reminder);
	}



	public StudentContact(String name, String surName, String phone, String email, String city, Course course,
			ArrayList<String> sourse, String comment, StatusContact statusContact, List<GroupForStudentDto> group,
			int cost_course, List<PaymentDto> payments, LinkedList<String> logs, Integer[] status_payment,
			ReminderDto reminder) {
		super();
		this.name = name;
		this.surName = surName;
		this.phone = phone;
		this.email = email;
		this.city = city;
		this.course = course;
		this.sourse = sourse;
		this.comment = comment;
		this.statusContact = statusContact;
		this.group = group;
		this.cost_course = cost_course;
		this.payments = payments;
		this.logs = logs;
		this.status_payment = status_payment;
		this.reminder = reminder;
	}
}
