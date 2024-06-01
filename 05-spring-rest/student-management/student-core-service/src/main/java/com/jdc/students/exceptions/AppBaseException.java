package com.jdc.students.exceptions;

import java.util.List;

public abstract class AppBaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private List<String> messages;

	public AppBaseException(List<String> messages) {
		super();
		this.messages = messages;
	}

	public AppBaseException(String message, Throwable cause) {
		super(message, cause);
		messages = List.of(message);
	}

	public AppBaseException(String message) {
		super(message);
		messages = List.of(message);
	}

	public List<String> getMessages() {
		return messages;
	}
}
