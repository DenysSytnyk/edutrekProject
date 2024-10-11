package telran.edutrek.student.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.edutrek.reminder.dto.ReminderDto;
import telran.edutrek.reminder.exceptions.ReminderDateNotValidException;
import telran.edutrek.student.dto.PaymentDto;
import telran.edutrek.student.dto.StudentDto;
import telran.edutrek.student.dto.StudentRegisterDto;
import telran.edutrek.student.dto.StudentUpdateDto;
import telran.edutrek.student.entities.StudentContact;
import telran.edutrek.student.exceptions.StudentAlreadyExistsException;
import telran.edutrek.student.exceptions.StudentNotFoundException;
import telran.edutrek.student.repo.StudentRepository;

@Service
public class StudentService implements IStudentManagement {

	@Autowired
	StudentRepository repo;

	@Override
	public StudentDto createStudent(StudentRegisterDto student) {
		if (repo.existsById(student.getId())) {
			throw new StudentAlreadyExistsException(student.getId());
		}
		StudentContact stud = new StudentContact(student.getId(), student.getName(), student.getSurName(),
				student.getPhone(), student.getEmail(), student.getCity(), student.getCourse(), student.getSourse(),
				student.getComment(), student.getStatusContact(), student.getGroup(), student.getCost_course(), null,
				null, student.getStatus_payment(), null);

		repo.save(stud);

		return new StudentDto(student.getId(), student.getName(), student.getSurName(), student.getPhone(),
				student.getEmail(), student.getCity(), student.getCourse(), student.getSourse(), student.getComment(),
				student.getStatusContact(), student.getGroup(), student.getCost_course(), null, null,
				student.getStatus_payment(), null);
	}

	@Override
	public StudentDto removeStudentsById(String id) {
		StudentContact student = getStudentContact(id);
		repo.delete(student);
		return student.build();
	}

	@Override
	public StudentDto getStudentById(String id) {
		return getStudentContact(id).build();
	}

	private StudentContact getStudentContact(String id) {
		return repo.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
	}

	@Override
	public List<StudentDto> getAllContact() {
		return repo.findAll().stream().map(student -> student.build()).collect(Collectors.toList());
	}

	@Override
	public List<StudentDto> getStudentsByName(String name) {

		return getAllContact().stream().filter(stud -> stud.getName().equalsIgnoreCase(name))
				.collect(Collectors.toList());

	}

	@Override
	public StudentDto updateStudentById(String id, StudentUpdateDto newStudentData) {
		if (!repo.existsById(id)) {
			throw new StudentNotFoundException(id);
		}
		StudentContact stud = getStudentContact(id);

		if (newStudentData.getName() == null) {
			stud.setName(stud.getName());
		}

		if (newStudentData.getSurName() == null) {
			stud.setSurName(stud.getSurName());
		}

		if (newStudentData.getPhone() == null) {
			stud.setPhone(stud.getPhone());
		}

		if (newStudentData.getCity() == null) {
			stud.setCity(stud.getCity());
		}

		if (newStudentData.getEmail() == null) {
			stud.setEmail(stud.getEmail());
		}

		if (newStudentData.getCourse() == null) {
			stud.setCourse(stud.getCourse());
		}

		if (newStudentData.getStatusContact() == null) {
			stud.setStatusContact(stud.getStatusContact());
		}

		stud.setName(newStudentData.getName());
		stud.setSurName(newStudentData.getSurName());
		stud.setPhone(newStudentData.getPhone());
		stud.setEmail(newStudentData.getEmail());
		stud.setCourse(newStudentData.getCourse());
		stud.setStatusContact(newStudentData.getStatusContact());
		stud.setCost_course(newStudentData.getCost_course());

		repo.save(stud);

		return stud.build();
	}

	@Override
	public boolean addCommentbyId(String id, String comment) {
		if (!repo.existsById(id)) {
			throw new StudentNotFoundException(id);
		}
		StudentContact stud = getStudentContact(id);

		if (comment != null && !comment.trim().isEmpty()) {
			stud.setComment(comment);
			repo.save(stud);

		}
		return true;
	}

	@Override
	public boolean addPaymentById(String id, PaymentDto payment) {
		if (!repo.existsById(id)) {
			throw new StudentNotFoundException(id);
		}
		StudentContact stud = getStudentContact(id);
		if (stud.getPayments() == null) {
			List<PaymentDto> payments = new ArrayList<PaymentDto>();
			payments.add(payment);
			stud.setPayments(payments);
			repo.save(stud);
			return true;
		}
		stud.getPayments().add(payment);
		repo.save(stud);
		return true;
	}

	@Override
	public boolean addReminderById(String id, ReminderDto reminder) {
		if (!repo.existsById(id)) {
			throw new StudentNotFoundException(id);
		}
		StudentContact stud = getStudentContact(id);
		if (reminder.getDate().isBefore(LocalDateTime.now())) {
			throw new ReminderDateNotValidException(reminder.getDate());
		}
		stud.setReminder(reminder);
		repo.save(stud);
		return true;
	}

}
