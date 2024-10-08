package telran.edutrek.accounting.entities;

import java.time.LocalDateTime;
import java.util.LinkedList;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import telran.edutrek.accounting.dto.UserAccountResponseDto;
import telran.edutrek.api.AccountingRoles;


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
	private AccountingRoles roles;
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
		this.revoked = false;
		roles = AccountingRoles.MODERATOR;

	}
	public UserAccountResponseDto build() 
	{
		return UserAccountResponseDto.builder().id(id).login(login).firstName(firstName)
				.lastName(lastName).roles(roles).revoked(revoked).build();
	}


}
