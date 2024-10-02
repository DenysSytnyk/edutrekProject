package telran.edutrek.accounting.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ContactRegisterDto 
{
	private String login;
	private String password;
	private String firstName;
	private String lastName;
}