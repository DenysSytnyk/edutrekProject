package telran.edutrek.accounting.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import static telran.edutrek.api.AccountingExceptionsMessages.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ContactRegisterDto 
{
	@NotNull(message = NOT_NULL)
	@NotBlank(message = NOT_BLANK)
	@Size(min = 3, message = LOGIN_SHORT)
	@Size(max = 20, message = LOGIN_LONG)
	private String login;
	
	@NotNull(message = NOT_NULL)
	@NotBlank(message = NOT_BLANK)
	private String password;
	
	@NotNull(message = NOT_NULL)
	@NotBlank(message = NOT_BLANK)
	private String firstName;
	
	@NotNull(message = NOT_NULL)
	@NotBlank(message = NOT_BLANK)
	private String lastName;
}
