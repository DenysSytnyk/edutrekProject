package telran.edutrek.accounting.service;

import java.util.List;

import telran.edutrek.accounting.dto.ContactRegisterDto;
import telran.edutrek.accounting.dto.UserAccountResponseDto;

public interface IAccountingManagement 
{
	UserAccountResponseDto addNewAccount(ContactRegisterDto account);
	UserAccountResponseDto deleteAccountById(String id);
	UserAccountResponseDto getAccountById(String id);
	UserAccountResponseDto getAccountByLogin(String login);
	List<UserAccountResponseDto> getAllAccounts();
	boolean changePasswordById(String id, String newPassword);
	boolean changeLoginById(String id, String newLogin);
	String getPasswordHash(String id);

	
}
