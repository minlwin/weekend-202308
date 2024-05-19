package com.jdc.balance.model.exceptions;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
	@ResponseStatus(value = HttpStatus.REQUEST_TIMEOUT)
	List<String> handle(ApiTokenExpirationException e) {
		return List.of(e.getMessage());
	}
	
	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	List<String> handle(AuthenticationException e) {
		return switch (e) {
		case UsernameNotFoundException ex -> List.of(ex.getMessage());
		case ApiTokenInvalidException ex -> List.of(ex.getMessage());
		case BadCredentialsException ex -> List.of("Please check your password.");
		case DisabledException ex -> List.of("Your account has been retired.");
		default -> List.of();
		};
	}
	
	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	List<String> handle(AccessDeniedException e) {
		return List.of(e.getMessage());
	}
	
	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	List<String> handle(Exception e) {
		e.printStackTrace();
		return List.of(e.getMessage());
	}
}
