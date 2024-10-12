package telran.edutrek.group.service;

import telran.edutrek.group.dto.GroupDto;
import telran.edutrek.group.dto.GroupUpdateDto;
import telran.edutrek.student.dto.StudentDto;

public interface IGroupManagement 
{
	GroupDto createGroup(GroupDto group);
	GroupDto updateGroupById(String id, GroupUpdateDto newGroupData);
	GroupDto getGroupById(String id);
	boolean addStudent(String groupId, String studentId);
	boolean moveStudent(String groupId, String studentId);
	StudentDto archiveStudentFromGroup(String groupId, String studentId);
	boolean deactivateGroupById(String id);
}
