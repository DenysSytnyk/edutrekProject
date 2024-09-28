package telran.edutrek.accounting.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import telran.edutrek.accounting.dto.ContactRegisterDto;
import telran.edutrek.accounting.dto.UserAccountResponseDto;
import telran.edutrek.accounting.service.IAccountingManagement;

@RestController
public class AccountingController  
{
	@Autowired
	IAccountingManagement service;

	@PostMapping("/auth/account")
	public UserAccountResponseDto addNewAccount(@RequestBody ContactRegisterDto account) {
		
		return service.addNewAccount(account);
	}
	
	@PostMapping("/auth")
	public UserAccountResponseDto login(Principal principal) {
	
		return service.getAccountById(principal.getName());
	}

	@DeleteMapping("/auth/{id}")
	public UserAccountResponseDto deleteAccountById(@PathVariable String id)
	{
		return service.deleteAccountById(id);
	}
//
//	@GetMapping
//	public UserAccountResponseDto getAccountById(String login) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@GetMapping
//	public List<UserAccountResponseDto> getAllAccounts() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@PutMapping
//	public boolean changePasswordById(String login, String newPassword) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@PutMapping
//	public boolean changeLoginById(String login, String newLogin) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@GetMapping
//	public String getPasswordHash(String login) {
//		return null;
//	}

}
