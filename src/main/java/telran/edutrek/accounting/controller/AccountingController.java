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
import org.springframework.web.bind.annotation.RequestHeader;
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

	@GetMapping("/auth/{id}")
	public UserAccountResponseDto getAccountById(@PathVariable String id) {
		
		return service.getAccountById(id);
	}

	@GetMapping("/auth")
	public List<UserAccountResponseDto> getAllAccounts() {
		List<UserAccountResponseDto> list=service.getAllAccounts();
		return list;
	}

	@PutMapping("/auth/password/{id}")
	public boolean changePasswordById(@PathVariable String id,@RequestHeader("X-New-Password") String newPassword ) {
		return service.changePasswordById(id, newPassword);
	}

//	@PutMapping
//	public boolean changeLoginById(String login, String newLogin) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
	@GetMapping("/auth/password/{id}")
	public String getPasswordHash(@PathVariable String id) {
		return service.getPasswordHash(id);
	}

}