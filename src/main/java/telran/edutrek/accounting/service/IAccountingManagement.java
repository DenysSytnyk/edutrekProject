package telran.edutrek.accounting.service;

import java.util.List;

import telran.edutrek.accounting.dto.ContactRegisterDto;
import telran.edutrek.accounting.dto.UserAccountResponseDto;

public interface IAccountingManagement 
{
	UserAccountResponseDto addNewAccount(ContactRegisterDto account);
	UserAccountResponseDto deleteAccountById(String login);
	UserAccountResponseDto getAccountById(String login);
	List<UserAccountResponseDto> getAllAccounts();
	boolean changePasswordById(String login, String newPassword);
	boolean changeLoginById(String login, String newLogin);
	String getPasswordHash(String login);

	
}
