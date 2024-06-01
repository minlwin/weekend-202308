package com.jdc.students.exceptions;

import java.util.List;

public class AppBusinessException extends AppBaseException{

	private static final long serialVersionUID = 1L;

	public AppBusinessException(List<String> messages) {
		super(messages);
	}

	public AppBusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public AppBusinessException(String message) {
		super(message);
	}

}
