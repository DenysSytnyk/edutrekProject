package telran.edutrek.group.service;

import java.util.List;

import telran.edutrek.group.dto.GroupDto;
import telran.edutrek.group.dto.GroupRegisterDto;
import telran.edutrek.group.dto.GroupUpdateDto;
import telran.edutrek.student.dto.StudentDto;

public interface IGroupManagement 
{
	GroupDto createGroup(GroupRegisterDto group);
	GroupDto deleteGroup(String id);
	GroupDto updateGroupById(String id, GroupUpdateDto newGroupData);
	GroupDto getGroupById(String id);
	List<GroupDto> getAllGroups();
	boolean addStudent(String groupId, String studentId);
	boolean moveStudent(String groupId, String studentId);
	boolean removeStudentFromGroup(String groupId, String studentId);
	StudentDto archiveStudentFromGroup(String groupId, String studentId);
	boolean deactivateGroupById(String id);
}
