package telran.edutrek.student.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.edutrek.group.entities.GroupData;
import telran.edutrek.group.repo.GroupRepository;
import telran.edutrek.reminder.dto.ReminderDto;
import telran.edutrek.reminder.exceptions.ReminderDateNotValidException;
import telran.edutrek.student.dto.PaymentDto;
import telran.edutrek.student.dto.PaymentUpdateDto;
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

	@Autowired
	GroupRepository groupRepo;

	@Override
	public StudentDto createStudent(StudentRegisterDto student) {
		if (repo.existsById(student.getPhone())) {
			throw new StudentAlreadyExistsException(student.getPhone());
		}
		StudentContact stud = new StudentContact(student.getName(), student.getSurName(), student.getPhone(),
				student.getEmail(), student.getCity(), student.getCourse(), student.getSourse(), student.getComment(),
				student.getStatusContact(), student.getGroup(), student.getCost_course(), null, null,
				student.getStatus_payment(), null);
		String createLog = LocalDate.now().toString() + " - student created";
		LinkedList<String> logsNew = new LinkedList<String>();
		stud.setLogs(logsNew);
		stud.getLogs().add(createLog);
		StudentContact s = repo.save(stud);

		return s.build();
	}

	@Override
	public StudentDto removeStudentsById(String id) {
		StudentContact student = getStudentContact(id);
		student.getGroup().forEach(g -> {
			GroupData group = groupRepo.findById(g.getId()).orElse(null);
			group.getStudents().removeIf((s) -> s.getId().equals(id));
			groupRepo.save(group);
		});
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
		} else {
			stud.setName(newStudentData.getName());
		}

		if (newStudentData.getSurName() == null) {
			stud.setSurName(stud.getSurName());
		} else {
			stud.setSurName(newStudentData.getSurName());
		}

		if (newStudentData.getPhone() == null) {
			stud.setPhone(stud.getPhone());
		} else {
			stud.setPhone(newStudentData.getPhone());
		}

		if (newStudentData.getCity() == null) {
			stud.setCity(stud.getCity());
		} else {
			stud.setCity(newStudentData.getCity());
		}

		if (newStudentData.getEmail() == null) {
			stud.setEmail(stud.getEmail());
		} else {
			stud.setEmail(newStudentData.getEmail());
		}

		if (newStudentData.getCourse() == null) {
			stud.setCourse(stud.getCourse());
		} else {
			stud.setCourse(newStudentData.getCourse());
		}

		if (newStudentData.getStatusContact() == null) {
			stud.setStatusContact(stud.getStatusContact());
		} else {
			stud.setStatusContact(newStudentData.getStatusContact());
		}

		stud.setCost_course(newStudentData.getCost_course());
		
		String createLog = LocalDate.now().toString() + " - student information has been updated";
		stud.getLogs().add(createLog);
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
			String createLog = LocalDate.now().toString() + " - comment has been added";
			stud.getLogs().add(createLog);
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
			String createLog = LocalDate.now().toString() + " - payment has been added";
			stud.getLogs().add(createLog);
			repo.save(stud);
			return true;
		}
		stud.getPayments().add(payment);
		String createLog = LocalDate.now().toString() + " - payment has been added";
		stud.getLogs().add(createLog);
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
		String createLog = LocalDate.now().toString() + " - reminder has been added";
		stud.getLogs().add(createLog);
		stud.setReminder(reminder);
		repo.save(stud);
		return true;
	}

	@Override
	public List<PaymentDto> updatePayment(String id, LocalDateTime date, PaymentUpdateDto payment) {
		if (!repo.existsById(id)) {
			throw new StudentNotFoundException(id);
		}
		StudentContact stud = getStudentContact(id);
		List<PaymentDto> newPayments = stud.getPayments().stream().filter(paym -> !paym.getDate().isEqual(date))
				.collect(Collectors.toList());
		PaymentDto updatedPayment = new PaymentDto(date, payment.getType(), payment.getAmount(),
				payment.getInstallments(), payment.getDetails());
		newPayments.add(updatedPayment);
		stud.setPayments(newPayments);
		String createLog = LocalDate.now().toString() + " - payment has been updated";
		stud.getLogs().add(createLog);
		repo.save(stud);
		return newPayments;

	}

	@Override
	public List<PaymentDto> deletePaymentByDate(String id, LocalDateTime date) {
		if (!repo.existsById(id)) {
			throw new StudentNotFoundException(id);
		}
		StudentContact stud = getStudentContact(id);
		List<PaymentDto> oldPayments = stud.getPayments().stream().filter(payment -> payment.getDate().isEqual(date))
				.collect(Collectors.toList());

		List<PaymentDto> newPayments = stud.getPayments().stream().filter(payment -> !payment.getDate().isEqual(date))
				.collect(Collectors.toList());
		stud.setPayments(newPayments);
		String createLog = LocalDate.now().toString() + " - payment has been deleted";
		stud.getLogs().add(createLog);
		repo.save(stud);
		return oldPayments;
	}

}
