package telran.edutrek.lecturers.service;

import java.util.List;

import telran.edutrek.lecturers.dto.LecturersDto;
import telran.edutrek.lecturers.dto.LecturersRegisterDto;
import telran.edutrek.lecturers.dto.LecturersUpdateDto;
import telran.edutrek.student.dto.GroupForStudentDto;

public interface ILecturersManagement {

	LecturersDto createLecturer(LecturersRegisterDto lecturer);
	LecturersDto removeLecturerById(String id);
	LecturersDto updateLecturer(LecturersUpdateDto newLecturer);
	LecturersDto getLecturerById(String id);
	LecturersDto addGroupToLecturer(String id,GroupForStudentDto group);
	LecturersDto removeGroupToLecturer(String id,GroupForStudentDto group);
	List<LecturersDto> getAllLecturer();
	List<LecturersDto> getLecturerByName(String name);
	
	
}
