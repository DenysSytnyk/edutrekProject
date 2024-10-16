package telran.edutrek.student.service;

import java.time.LocalDateTime;
import java.util.List;

import telran.edutrek.reminder.dto.ReminderDto;
import telran.edutrek.student.dto.PaymentDto;
import telran.edutrek.student.dto.PaymentUpdateDto;
import telran.edutrek.student.dto.StudentDto;
import telran.edutrek.student.dto.StudentRegisterDto;
import telran.edutrek.student.dto.StudentUpdateDto;
import telran.edutrek.student.entities.StudentContact;

public interface IStudentManagement {
	StudentDto createStudent(StudentRegisterDto student);

	StudentDto removeStudentsById(String id);

	StudentDto getStudentById(String id);

	StudentDto updateStudentById(String id, StudentUpdateDto newStudentData);

	List<PaymentDto> updatePayment(String id, LocalDateTime date, PaymentUpdateDto apdatedPayment);

	List<PaymentDto> deletePaymentByDate(String id, LocalDateTime date);

	List<StudentDto> getAllContact();

	List<StudentDto> getStudentsByName(String name);

	boolean updateCommentbyId(String id, String comment);

	boolean addPaymentById(String id, PaymentDto payment);

	boolean addReminderById(String id, ReminderDto reminder);
}
