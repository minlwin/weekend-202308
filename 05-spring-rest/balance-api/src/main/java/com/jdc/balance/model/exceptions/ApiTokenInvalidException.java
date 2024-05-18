package com.jdc.balance.model.exceptions;

import org.springframework.security.core.AuthenticationException;

public class ApiTokenInvalidException extends AuthenticationException{

	private static final long serialVersionUID = 1L;

	public ApiTokenInvalidException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
