package telran.edutrek.tools.status.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telran.edutrek.tools.status.dto.StatusDto;
import telran.edutrek.tools.status.entities.StatusData;
import telran.edutrek.tools.status.exceptions.InvalidStatusNameException;
import telran.edutrek.tools.status.exceptions.StatusExistsException;
import telran.edutrek.tools.status.exceptions.StatusNotFoundException;
import telran.edutrek.tools.status.repo.StatusRepo;

@Service
public class StatusService implements StatusManagement
{
	@Autowired
	StatusRepo repo;

	@Override
	public StatusDto createStatus(String name) 
	{
		if (name == null || name.isBlank()) 
			throw new InvalidStatusNameException(name);
		
		if (repo.findByName(name).orElse(null) != null) 
			throw new StatusExistsException(name);
		
		return repo.save(new StatusData(name)).build();
	}

	@Override
	public List<StatusDto> getAllStatuses() 
	{
		return repo.findAll().stream().map(s -> s.build()).toList();
	}

	@Override
	public StatusDto getStatusById(String id) 
	{
		return findStatus(id).build();
	}

	@Override
	public StatusDto updateStatusById(String id, String newName) 
	{
		StatusData data = findStatus(id);
		if (newName == null || newName.isBlank() || data.getName().equals(newName)) 
			throw new InvalidStatusNameException(newName);
		
		data.setName(newName);
		return repo.save(data).build();
	}

	@Override
	public StatusDto removeStatusById(String id) 
	{
		StatusDto status = findStatus(id).build();
		repo.deleteById(id);
		return status;
	}

	private StatusData findStatus(String id) 
	{
		return repo.findById(id).orElseThrow(() -> new StatusNotFoundException(id));
	}
}
