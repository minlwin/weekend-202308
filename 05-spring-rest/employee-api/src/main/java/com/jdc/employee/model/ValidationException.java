package com.jdc.employee.model;

import java.util.List;

public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private List<String> messages;

	public ValidationException(List<String> messages) {
		super();
		this.messages = messages;
	}

	public List<String> getMessages() {
		return messages;
	}

}
