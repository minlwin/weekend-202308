package com.jdc.balance.model.exceptions;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandlers {

	@ExceptionHandler({
		ApiBusinessException.class,
		ApiValidationException.class
	})
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	List<String> handle(ApiBaseException e) {
		return e.getMessages();
	}
	
	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	List<String> handle(ApiTokenExpirationException e) {
		return List.of(e.getMessage());
	}
	
	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.PAYMENT_REQUIRED)
	List<String> handle(ApiTokenInvalidException e) {
		return List.of(e.getMessage());
	}

	@ExceptionHandler({
		AccessDeniedException.class,
		DisabledException.class
	})
	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	List<String> handle(RuntimeException e) {
		return List.of(e.getMessage());
	}
	
	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	List<String> handle(Exception e) {
		e.printStackTrace();
		return List.of(e.getMessage());
	}
}
