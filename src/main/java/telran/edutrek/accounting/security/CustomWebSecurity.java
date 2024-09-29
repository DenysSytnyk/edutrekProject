package telran.edutrek.accounting.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telran.edutrek.repo.EdutrekRepository;

@Service
public class CustomWebSecurity
{
	@Autowired
	EdutrekRepository repo;
	
	public boolean checkOwner(String login)
	{
		return repo.findById(login).orElse(null) != null; 
	}
}
