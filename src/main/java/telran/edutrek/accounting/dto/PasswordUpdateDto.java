package telran.edutrek.accounting.dto;

import static telran.edutrek.api.AccountingExceptionsMessages.NOT_BLANK;
import static telran.edutrek.api.AccountingExceptionsMessages.NOT_NULL;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PasswordUpdateDto 
{
	@NotNull(message = NOT_NULL)
	@NotBlank(message = NOT_BLANK)
	private String password;
}
