package com.jdc.students.exceptions;

import java.util.List;

public class AppValidationException extends AppBaseException{

	private static final long serialVersionUID = 1L;

	public AppValidationException(List<String> messages) {
		super(messages);
	}

}
