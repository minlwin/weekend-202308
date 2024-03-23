package com.jdc.rest.exceptions;

import java.util.List;

public class ApiValidationException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private List<String> messages;

	public ApiValidationException(List<String> messages) {
		super();
		this.messages = messages;
	}
	
	public List<String> getMessages() {
		return messages;
	}

}
