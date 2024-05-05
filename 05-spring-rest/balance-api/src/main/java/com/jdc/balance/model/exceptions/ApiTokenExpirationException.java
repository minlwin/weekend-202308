package com.jdc.balance.model.exceptions;

import org.springframework.security.core.AuthenticationException;

public class ApiTokenExpirationException extends AuthenticationException{

	private static final long serialVersionUID = 1L;

	public ApiTokenExpirationException() {
		super("Token has been expired at this moment.");
	}

}
