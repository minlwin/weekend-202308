package com.jdc.students.exceptions;

import org.springframework.security.core.AuthenticationException;

public class AppTokenExpirationException extends AuthenticationException{

	private static final long serialVersionUID = 1L;
	
	private static final String MESSAGE = "Your access token is expired. Please refresh token again.";

	public AppTokenExpirationException(Throwable cause) {
		super(MESSAGE, cause);
	}
	
	public AppTokenExpirationException() {
		super(MESSAGE);
	}

}
