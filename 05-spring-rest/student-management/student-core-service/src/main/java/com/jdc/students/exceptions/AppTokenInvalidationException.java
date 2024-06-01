package com.jdc.students.exceptions;

import org.springframework.security.core.AuthenticationException;

public class AppTokenInvalidationException extends AuthenticationException{

	private static final long serialVersionUID = 1L;

	public AppTokenInvalidationException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
