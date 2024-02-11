package com.jdc.weekend.model;

public class AppBusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AppBusinessException(String message) {
		super(message);
	}

}
