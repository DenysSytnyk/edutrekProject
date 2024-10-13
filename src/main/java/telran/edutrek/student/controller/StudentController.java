package telran.edutrek.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import telran.edutrek.accounting.dto.UserAccountResponseDto;
import telran.edutrek.reminder.dto.ReminderDto;
import telran.edutrek.student.dto.PaymentDto;
import telran.edutrek.student.dto.StudentDto;
import telran.edutrek.student.dto.StudentRegisterDto;
import telran.edutrek.student.dto.StudentUpdateDto;
import telran.edutrek.student.service.IStudentManagement;

@RestController
public class StudentController {

	@Autowired
	IStudentManagement service;

	@PostMapping("/students/add")
	public StudentDto createStudent(@RequestBody StudentRegisterDto student) {

		return service.createStudent(student);
	}

	@DeleteMapping("/students/{id}")
	public StudentDto removeStudentsById(@PathVariable String id) {

		return service.removeStudentsById(id);
	}

	@GetMapping("/students/id/{id}")
	public StudentDto getStudentById(@PathVariable String id) {
		return service.getStudentById(id);
	}

	@GetMapping("/students")
	public List<StudentDto> getAllContact() {
		return service.getAllContact();
	}

	@GetMapping("/students/name/{name}")
	public List<StudentDto> getStudentsByName(@PathVariable String name) {
		return service.getStudentsByName(name);
	}

	@PutMapping("/students/update/{id}")
	public StudentDto updateStudentById(@PathVariable String id, @RequestBody StudentUpdateDto newStudentData) {
		return service.updateStudentById(id, newStudentData);
	}

	@PutMapping("/students/comment/{id}")
	public boolean addCommentbyId(@PathVariable String id, @RequestBody String comment) {
		return service.addCommentbyId(id, comment);
	}

	@PutMapping("/students/payments/{id}")
	public boolean addPaymentById(@PathVariable String id, @RequestBody PaymentDto payment) {

		return service.addPaymentById(id, payment);
	}

	@PutMapping("/students/reminder/{id}")
	public boolean addReminderById(@PathVariable String id, @RequestBody ReminderDto reminder) {
		return service.addReminderById(id, reminder);
	}

}
