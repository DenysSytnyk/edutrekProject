package telran.edutrek.student.dto;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import telran.edutrek.contact.dto.UserContactDto;
import telran.edutrek.group.dto.GroupDto;
import telran.edutrek.group.dto.StudentForGroupDto;
import telran.edutrek.reminder.dto.ReminderDto;
import telran.edutrek.utils.Course;
import telran.edutrek.utils.StatusContact;


@Getter
@NoArgsConstructor
public class StudentDto extends UserContactDto
{
	private List<GroupForStudentDto> group;
	private int cost_course;
	private List<PaymentDto> payments;
	private LinkedList<String> logs;
	private Integer[] status_payment;
	private ReminderDto reminder;
	
	public StudentDto(String id, String name, String surName, String phone, String email, String city, Course course,
			ArrayList<String> sourse, String comment, StatusContact statusContact, List<GroupForStudentDto> group, int cost_course,
			List<PaymentDto> payments, LinkedList<String> logs, Integer[] status_payment, ReminderDto reminder) {
		super( name, surName, phone, email, city, course, sourse, comment, statusContact);
		this.group = group;
		this.cost_course = cost_course;
		this.payments = payments;
		this.logs = logs;
		this.status_payment = status_payment;
		this.reminder = reminder;
	}
	
	
}
