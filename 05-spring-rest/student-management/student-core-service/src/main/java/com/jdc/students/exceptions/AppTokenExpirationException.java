package com.jdc.students.exceptions;

import org.springframework.security.core.AuthenticationException;

public class AppTokenExpirationException extends AuthenticationException{

	private static final long serialVersionUID = 1L;

	public AppTokenExpirationException(Throwable cause) {
		super("Your access token is expired. Please refresh token again.", cause);
	}

}
