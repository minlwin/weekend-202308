package com.jdc.balance.model.exceptions;

public class ApiBusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ApiBusinessException(String message) {
		super(message);
	}

}
