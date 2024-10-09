package telran.edutrek.student.service;

import java.util.List;

import telran.edutrek.reminder.dto.ReminderDto;
import telran.edutrek.student.dto.PaymentDto;
import telran.edutrek.student.dto.StudentDto;
import telran.edutrek.student.dto.StudentRegisterDto;
import telran.edutrek.student.dto.StudentUpdateDto;

public interface IStudentManagement 
{
	StudentDto createStudent(StudentRegisterDto student);
	StudentDto removeStudentsById(String id);
	StudentDto getStudentById(String id);
	StudentDto updateStudentById(StudentUpdateDto newStudentData);
	List<StudentDto> getAllContact();
	List<StudentDto> getStudentsByName(String name);
	boolean addCommentbyId(String id, String comment);
	boolean addPaymentById(String id, PaymentDto payment);
	boolean addReminderById(String id, ReminderDto reminder);
}
