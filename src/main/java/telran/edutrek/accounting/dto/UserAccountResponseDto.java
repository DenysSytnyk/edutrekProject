package telran.edutrek.accounting.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import telran.edutrek.accounting.entities.UserAccount;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class UserAccountResponseDto {
	private String id;
	private String login;
	private String firstName;
	private String lastName;
	private Set<String> roles;
	
	static UserAccountResponseDto build(UserAccount user)
	{
		return new UserAccountResponseDto(user.getId(), user.getLogin(), user.getFirstName(), user.getLastName(), user.getRoles());
	}
}
