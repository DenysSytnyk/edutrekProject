package telran.edutrek.lecturers.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import telran.edutrek.lecturers.dto.LecturersDto;
import telran.edutrek.lecturers.dto.LecturersRegisterDto;
import telran.edutrek.lecturers.dto.LecturersUpdateDto;
import telran.edutrek.lecturers.service.ILecturersManagement;
import telran.edutrek.student.dto.GroupForStudentDto;
@RequestMapping("/lecturer")
@RestController
public class LecturersController {

	@Autowired
	ILecturersManagement service;
	
	@PostMapping("/add")
	public LecturersDto createLecturer(@RequestBody LecturersRegisterDto lecturer) {
		
		return service.createLecturer(lecturer);
	}

	@DeleteMapping("/{id}")
	public LecturersDto removeLecturerById(@PathVariable String id) {
		
		return service.removeLecturerById(id);
	}

	@PutMapping("/update/{id}")
	public LecturersDto updateLecturer(@PathVariable String id ,@RequestBody LecturersUpdateDto newLecturer) {
		
		return service.updateLecturer(id,newLecturer);
	}

	@GetMapping("/id/{id}")
	public LecturersDto getLecturerById(@PathVariable String id) {
		
		return service.getLecturerById(id);
	}

	@PutMapping("/add/group/{id}")
	public LecturersDto addGroupToLecturer(@PathVariable String id,@RequestBody GroupForStudentDto group) {
		
		return service.addGroupToLecturer(id, group);
	}

	@DeleteMapping("/remove/group/{id}")
	public LecturersDto removeGroupToLecturer(@PathVariable String id,@RequestBody GroupForStudentDto group) {
		
		return service.removeGroupToLecturer(id, group);
	}

	@GetMapping("/all")
	public List<LecturersDto> getAllLecturer() {
		
		return service.getAllLecturer();
	}

	@GetMapping("/name/{name}")
	public List<LecturersDto> getLecturerByName(@PathVariable String name) {
		
		return service.getLecturerByName(name);
	}

}
