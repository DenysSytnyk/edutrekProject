package telran.edutrek.tools.status.service;

import java.util.List;

import telran.edutrek.tools.status.dto.StatusDto;

public interface StatusManagement 
{
	StatusDto createStatus(String name);
	List<StatusDto> getAllStatuses();
	StatusDto getStatusById(String id);
	StatusDto updateStatusById(String id, String newName);
	StatusDto removeStatusById(String id);
	
}
