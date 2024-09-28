package telran.edutrek.accounting.service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import telran.edutrek.accounting.dto.ContactRegisterDto;
import telran.edutrek.accounting.dto.UserAccountResponseDto;
import telran.edutrek.accounting.entities.UserAccount;
import telran.edutrek.accounting.exceptions.UserExistsException;
import telran.edutrek.accounting.exceptions.UserNotFoundException;
import telran.edutrek.repo.EdutrekRepository;

@Service
public class AccountingService implements IAccountingManagement, CommandLineRunner
{
	
	@Autowired
	EdutrekRepository repo;
	
	@Autowired
	PasswordEncoder encoder;
	
	private int passLength = 6;

	@Override
	public UserAccountResponseDto addNewAccount(ContactRegisterDto account) {
		if (repo.existsById(account.getLogin())) 
			throw new UserExistsException(account.getLogin());
		if (isPasswordValid(account.getPassword())) 
			throw new ResponseStatusException(HttpStatus.CONFLICT, 
					"Password" + account.getPassword() + " is not valid");
		
		UserAccount acc = new UserAccount(account.getLogin(), getHash(account.getPassword()),
				account.getFirstName(), account.getLastName());
		
		repo.save(acc);
		return new UserAccountResponseDto(account.getLogin(), account.getFirstName(), account.getLastName(), acc.getRoles());
	}

	private String getHash(String password) {
		return encoder.encode(password);
	}

	private boolean isPasswordValid(String password) {
		return password.length()<=passLength;
	}

	@Override
	public UserAccountResponseDto deleteAccountById(String login) {
		UserAccount acc = getUserAccount(login);	
		repo.delete(acc);
		return acc.build();
	}
	private UserAccount getUserAccount(String login)
	{
		return repo.findById(login).orElseThrow(() -> new UserNotFoundException(login));
	}
	@Override
	public UserAccountResponseDto getAccountById(String login) {
		return getUserAccount(login).build();
	}

	@Override
	public List<UserAccountResponseDto> getAllAccounts() 
	{
		List<UserAccountResponseDto> res = repo.findAll().stream().map(ua -> ua.build()).collect(Collectors.toList());
		return res;
	}

	@Override
	public boolean changePasswordById(String login, String newPassword) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changeLoginById(String login, String newLogin) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getPasswordHash(String login) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void run(String... args) throws Exception
	{
		if (!repo.existsById("admin"))
		{
			UserAccount admin = new UserAccount("admin", encoder.encode("administrator"), "", "");
			admin.setRoles(new HashSet<String>(List.of("ADMIN")));
			repo.save(admin);
		}
	}

}
