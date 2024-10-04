package telran.edutrek.api;

public interface AccountingExceptionsMessages 
{
	String NOT_NULL = "There shouldn't be null";
	String NOT_BLANK = "There shouldn't be blank";
	String LOGIN_SHORT = "Login must be at least 3 characters long";
	String LOGIN_LONG = "Login cannot be longer than 20 characters";
	String PASSWORD_NOT_VALID = "Password is not valid: ";
	String LOGIN_NOT_VALID = "Login is not valid: ";
	String USER_EXIST = "User exist: ";
	String USER_NOT_FOUND = "User not found: ";
}
