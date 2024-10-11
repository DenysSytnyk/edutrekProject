package telran.edutrek.accounting.service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import telran.edutrek.accounting.dto.ContactRegisterDto;
import telran.edutrek.accounting.dto.UserAccountResponseDto;
import telran.edutrek.accounting.entities.UserAccount;
import telran.edutrek.accounting.exceptions.*;
import telran.edutrek.accounting.repo.EdutrekRepository;
import telran.edutrek.api.AccountingRoles;

@Service
public class AccountingService implements IAccountingManagement, CommandLineRunner
{
	
	@Autowired
	EdutrekRepository repo;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Value("${password_lenghth:6}")
	private int passLength = 6;
	@Value("${amount_last_hash:3}")
	private int amountLastHast = 3;
	
	

	@Override
	public UserAccountResponseDto addNewAccount(ContactRegisterDto account) {
		
		if (repo.existsById(account.getLogin())) 
			throw new UserExistsException(account.getLogin());
		if (isPasswordValid(account.getPassword())) 
			throw new PasswordNotValidException(account.getPassword());
		
		UserAccount acc = new UserAccount(account.getLogin(), getHash(account.getPassword()),
				account.getFirstName(), account.getLastName());
		
		repo.save(acc);
		return new UserAccountResponseDto(acc.getId(), account.getLogin(), account.getFirstName(),
				account.getLastName(), acc.getRoles(), acc.isRevoked());
	}

	private String getHash(String password) {
		return encoder.encode(password);
	}

	private boolean isPasswordValid(String password) {
		return password.length()<=passLength;
	}

	@Override
	public UserAccountResponseDto deleteAccountById(String id) {
		UserAccount acc = getUserAccount(id);	
		repo.delete(acc);
		return acc.build();
	}
	private UserAccount getUserAccount(String id)
	{
		return repo.findById(id).orElseThrow(() -> new UserNotFoundException( id));
	}
	@Override
	public UserAccountResponseDto getAccountById(String id) {
		return getUserAccount(id).build();
	}

	@Override
	public List<UserAccountResponseDto> getAllAccounts() 
	{
		List<UserAccountResponseDto> res = repo.findAll().stream().map(ua -> ua.build()).collect(Collectors.toList());
		return res;
	}

	@Override
	public boolean changePasswordById(String id, String newPassword) {
		if(newPassword==null|| isPasswordValid(newPassword))
			throw  new PasswordNotValidException(newPassword);
		UserAccount user=getUserAccount(id);
		if(encoder.matches(newPassword, user.getHashCode()))
			throw new PasswordNotValidException(newPassword);
		LinkedList<String> lastHash=user.getLastHashCodes();
		if(isPasswordFromLast(newPassword,lastHash))
			throw  new PasswordNotValidException(newPassword);
		if(lastHash.size()==amountLastHast)
			lastHash.removeFirst();
		lastHash.add(user.getHashCode());
		user.setHashCode(encoder.encode(newPassword));
		user.setActivationDate(LocalDateTime.now());
		repo.save(user);
		return true;
	}

	private boolean isPasswordFromLast(String newPassword, LinkedList<String> lastHash) {
		return lastHash.stream().anyMatch(p->
		encoder.matches(newPassword, p));
	}

	@Override
	public boolean changeLoginById(String id, String newLogin) 
	{
		UserAccount user=getUserAccount(id);
		List<String> list = repo.findAll().stream().map(ua -> ua.getLogin()).toList();
		
		if(newLogin==null ||newLogin.equals(user.getLogin()) || repo.existsById(newLogin) || list.contains(newLogin))
			throw new LoginNotValidException(newLogin);
		
		user.setLogin(newLogin);
		user.setActivationDate(LocalDateTime.now());
		repo.save(user);
		
		return true;
	}

	@Override
	public String getPasswordHash(String id) {
		UserAccount user=getUserAccount(id);
		return user.getHashCode();
	}
	
	@Override
	public UserAccountResponseDto getAccountByLogin(String login) 
	{
		return repo.findByLogin(login)
				.orElseThrow(() -> new UsernameNotFoundException(login)).build();
	}
	
	@Override
	public boolean blockUser(String id) 
	{
		UserAccount user=getUserAccount(id);
		user.setRevoked(true);
		repo.save(user);
		return true;
	}

	@Override
	public boolean activateUser(String id) 
	{
		UserAccount user=getUserAccount(id);
		user.setRevoked(false);
		repo.save(user);
		return true;
	}
	
	@Override
	public void run(String... args) throws Exception
	{
		
		if (!repo.existsById("admin"))
		{
			UserAccount admin = new UserAccount("admin", encoder.encode("administrator"), "", "");
			admin.setRoles(AccountingRoles.ADMIN);
			repo.save(admin);
		}
	}

	@Override
	public boolean changeFirstNameById(String id, String newFirstName) {
		UserAccount user=getUserAccount(id);
		if(newFirstName==null || repo.existsById(newFirstName))
			throw new NameNotValidException(newFirstName);
		
		user.setFirstName(newFirstName);
		user.setActivationDate(LocalDateTime.now());
		repo.save(user);
		return true;
	}

	@Override
	public boolean changeLastNameById(String id, String newLastName) {
		UserAccount user=getUserAccount(id);
		if(newLastName==null || repo.existsById(newLastName))
			throw new NameNotValidException(newLastName);
		
		user.setFirstName(newLastName);
		user.setActivationDate(LocalDateTime.now());
		repo.save(user);
		return true;
	}





}
