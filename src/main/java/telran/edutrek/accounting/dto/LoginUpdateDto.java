package telran.edutrek.accounting.dto;

import static telran.edutrek.api.AccountingExceptionsMessages.LOGIN_LONG;
import static telran.edutrek.api.AccountingExceptionsMessages.LOGIN_SHORT;
import static telran.edutrek.api.AccountingExceptionsMessages.NOT_BLANK;
import static telran.edutrek.api.AccountingExceptionsMessages.NOT_NULL;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LoginUpdateDto 
{
	@NotNull(message = NOT_NULL)
	@NotBlank(message = NOT_BLANK)
	@Size(min = 3, message = LOGIN_SHORT)
	@Size(max = 20, message = LOGIN_LONG)
	private String login;
}
