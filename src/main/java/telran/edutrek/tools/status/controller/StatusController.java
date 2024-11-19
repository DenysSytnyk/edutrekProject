package telran.edutrek.tools.status.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import telran.edutrek.tools.status.dto.StatusDto;
import telran.edutrek.tools.status.service.StatusManagement;

@RestController
@RequestMapping("/status")
public class StatusController
{
	@Autowired
	StatusManagement service;

	@PostMapping("/add/{name}")
	public StatusDto createStatus(@PathVariable String name) 
	{
		return service.createStatus(name);
	}

	@GetMapping("/all")
	public List<StatusDto> getAllStatuses() 
	{
		return service.getAllStatuses();
	}

	@GetMapping("/{id}")
	public StatusDto getStatusById(@PathVariable String id)
	{
		return service.getStatusById(id);
	}

	@PutMapping("/update/{id}/{newName}")
	public StatusDto updateStatusById(@PathVariable String id, @PathVariable String newName) 
	{
		return service.updateStatusById(id, newName);
	}

	@DeleteMapping("/{id}")
	public StatusDto removeStatusById(@PathVariable String id) 
	{
		return service.removeStatusById(id);
	}
	

}
