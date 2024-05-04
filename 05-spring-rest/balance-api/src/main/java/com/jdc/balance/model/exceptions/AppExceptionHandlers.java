package com.jdc.balance.model.exceptions;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandlers {

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	List<String> handle(ApiValidationException e) {
		return e.getMessages();
	}
	
	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	List<String> handle(ApiBusinessException e) {
		return List.of(e.getMessage());
	}
	
}
