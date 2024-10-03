package telran.edutrek.accounting.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedList;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import telran.edutrek.accounting.dto.UserAccountResponseDto;


@Getter
@Setter
@Document(collection = "edutrek_account")
public class UserAccount {

	@Id
	@Setter(value = AccessLevel.NONE)
	private String id;
	private String login;
	private String hashCode;
	private String firstName;
	private String lastName;
	private HashSet<String> roles;
	private LocalDateTime activationDate;
	private boolean revoked;
	private LinkedList<String> lastHashCodes = new LinkedList<String>();




	public UserAccount(String login, String hashCode, String firstName, String lastName) {
		super();
		this.id = login;
		this.login = login;
		this.hashCode = hashCode;
		this.firstName = firstName;
		this.lastName = lastName;
		this.activationDate = LocalDateTime.now();
		roles = new HashSet<String>();
		roles.add("USER");

	}
	public UserAccountResponseDto build() 
	{
		return UserAccountResponseDto.builder().id(login).login(login).firstName(firstName)
				.lastName(lastName).roles(roles).build();
	}


}
