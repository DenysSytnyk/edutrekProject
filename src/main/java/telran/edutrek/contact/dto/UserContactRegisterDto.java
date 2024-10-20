package telran.edutrek.contact.dto;


import static telran.edutrek.api.AccountingExceptionsMessages.NOT_BLANK;
import static telran.edutrek.api.AccountingExceptionsMessages.NOT_NULL;

import java.util.ArrayList;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import telran.edutrek.utils.Course;
import telran.edutrek.utils.StatusContact;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserContactRegisterDto {

	@NotNull(message = NOT_NULL)
	@NotBlank(message = NOT_BLANK)
	private String name;
	@NotNull(message = NOT_NULL)
	@NotBlank(message = NOT_BLANK)
	private String surName;
	@Pattern(regexp = "^\\d{10}$")
	private String phone;
	@Pattern(regexp = "[^\\s,.][^\\s,]*@([A-Za-z\\d-]+\\.)?(co\\.il|mail\\.ru|gmail\\.com)")	
	private String email;
	@NotNull(message = NOT_NULL)
	@NotBlank(message = NOT_BLANK)
	private String city;
	private Course course;
	@NotNull(message = NOT_NULL)
	private ArrayList<String> sourse;
	@NotNull(message = NOT_NULL)
	@NotBlank(message = NOT_BLANK)
	private String comment;
	@NotNull(message = NOT_NULL)
	private StatusContact statusContact;
}
