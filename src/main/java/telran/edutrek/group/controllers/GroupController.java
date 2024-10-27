package telran.edutrek.group.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import telran.edutrek.group.dto.GroupDto;
import telran.edutrek.group.dto.GroupRegisterDto;
import telran.edutrek.group.dto.GroupUpdateDto;
import telran.edutrek.group.service.IGroupManagement;
import telran.edutrek.reminder.dto.ReminderDto;
import telran.edutrek.student.dto.StudentDto;

@RestController
public class GroupController
{
	@Autowired
	IGroupManagement service;

	@PostMapping("/group/create")
	public GroupDto createGroup(@RequestBody GroupRegisterDto group) 
	{
		return service.createGroup(group);
	}

	@PutMapping("/group/update/{id}")
	public GroupDto updateGroupById(@PathVariable String id,@RequestBody GroupUpdateDto newGroupData) 
	{
		return service.updateGroupById(id, newGroupData);
	}

	@GetMapping("/group/{id}")
	public GroupDto getGroupById(@PathVariable String id) {
		return service.getGroupById(id);
	}
	
	@GetMapping("/group/name/{name}")
	public GroupDto getGroupByName(@PathVariable String name) {
		return service.getGroupByName(name);
	}
	
	@DeleteMapping("/group/{id}")
	public GroupDto deleteGroupById(@PathVariable String id) {
		return service.deleteGroup(id);
	}
	
	@DeleteMapping("/group/student/{groupId}/{studentId}")
	public boolean deleteStudentFromGroupById(@PathVariable String groupId,@PathVariable String studentId)
	{
		return service.removeStudentFromGroup(groupId, studentId);
	}
	
	@GetMapping("/group")
	public List<GroupDto> getAllGroups() 
	{
		return service.getAllGroups();
	}

	@PutMapping("/group/add/{groupId}/{studentId}")
	public boolean addStudent(@PathVariable String groupId,@PathVariable String studentId) 
	{
		return service.addStudent(groupId, studentId);
	}

	@PutMapping("/group/move/{groupId}/{studentId}")
	public boolean moveStudent(@PathVariable String groupId,@PathVariable String studentId) 
	{
		return service.moveStudent(groupId, studentId);
	}

	@PutMapping("/group/archive/{groupId}/{studentId}")
	public StudentDto archiveStudentFromGroup(@PathVariable String groupId,@PathVariable String studentId) 
	{
		return service.archiveStudentFromGroup(groupId, studentId);
	}
	
	@PutMapping("/group/reminder/{groupId}")
	public boolean addReminderToGroup(@PathVariable String groupId, @RequestBody String reminder) 
	{
		return service.addReminderGroupById(groupId, reminder);
	}


}
