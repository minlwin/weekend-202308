package com.jdc.students.exceptions;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlers {
	
	@Value("${app.security.username.notfound}")
	private String userNameNotFound;
	@Value("${app.security.bad.credential}")
	private String badCredential;
	@Value("${app.security.account.expired}")
	private String accountExpired;
	@Value("${app.security.disabled}")
	private String disabled;
	
	@Value("${app.security.access.denied}")
	private String accessDenined;
	
	@ExceptionHandler({
		AppBusinessException.class,
		AppValidationException.class
	})
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public List<String> handle(AppBaseException e) {
		return e.getMessages();
	}
	
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.REQUEST_TIMEOUT)
	public List<String> handle(AppTokenExpirationException e) {
		return List.of(e.getMessage());
	}

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	public List<String> handle(AuthenticationException e) {
		return switch(e) {
		case DisabledException ex -> List.of(disabled);
		case UsernameNotFoundException ex -> List.of(userNameNotFound);
		case BadCredentialsException ex -> List.of(badCredential);
		case AccountExpiredException ex -> List.of(accountExpired);
		default -> List.of(e.getMessage());
		};
	}	
	
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.FORBIDDEN)
	public List<String> handle(AccessDeniedException e) {
		return List.of(accessDenined);
	}
	
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public List<String> handle(Exception e) {
		e.printStackTrace();
		return List.of(e.getMessage());
	}
	
}
