package com.broglog.signup;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.*;

import com.broglog.account.Account;

@FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match")
public class SignupForm {

	private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";
	private static final String EMAIL_MESSAGE = "{email.message}";
	private static final String NOT_STRONG_PASSWORD_MESSAGE = "{notStrongPassword.message}";

    @NotBlank(message = SignupForm.NOT_BLANK_MESSAGE)
	@Email(message = SignupForm.EMAIL_MESSAGE)
	private String email;

    @NotNull
    @Pattern(regexp="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})", message = SignupForm.NOT_STRONG_PASSWORD_MESSAGE)
    @NotBlank(message = SignupForm.NOT_BLANK_MESSAGE)
	private String password;
    
    private String confirmPassword;

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getConfirmPassword()
	{
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword)
	{
		this.confirmPassword = confirmPassword;
	}
	
	@AssertTrue(message="Passwords don't match")
	private boolean match()
	{
		return this.password.equals(confirmPassword);
	}

	public Account createAccount() {
        return new Account(getEmail(), getPassword(), "ROLE_USER");
	}


}
