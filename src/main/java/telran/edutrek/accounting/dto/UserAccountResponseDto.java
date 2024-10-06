package telran.edutrek.accounting.dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import telran.edutrek.accounting.entities.UserAccount;
import telran.edutrek.api.AccountingRoles;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class UserAccountResponseDto {
	private String id;
	private String login;
	private String firstName;
	private String lastName;
	private AccountingRoles roles;
	boolean revoked;
	
	static UserAccountResponseDto build(UserAccount user)
	{
		return new UserAccountResponseDto(user.getId(), user.getLogin(), user.getFirstName(), user.getLastName(),
				user.getRoles(), user.isRevoked());
	}
}
