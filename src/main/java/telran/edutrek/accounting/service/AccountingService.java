package telran.edutrek.accounting.service;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import telran.edutrek.accounting.dto.ContactRegisterDto;
import telran.edutrek.accounting.dto.UserAccountResponseDto;
import telran.edutrek.accounting.entities.UserAccount;
import telran.edutrek.accounting.exceptions.UserExistsException;
import telran.edutrek.repo.EdutrekRepository;

@Service
public class AccountingService implements IAccountingManagement
{
	
	@Autowired
	EdutrekRepository repo;
	
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
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}

	private boolean isPasswordValid(String password) {
		return password.length()<=passLength;
	}

	@Override
	public UserAccountResponseDto deleteAccountById(String login) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserAccountResponseDto getAccountById(String login) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserAccountResponseDto> getAllAccounts() {
		// TODO Auto-generated method stub
		return null;
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

}
