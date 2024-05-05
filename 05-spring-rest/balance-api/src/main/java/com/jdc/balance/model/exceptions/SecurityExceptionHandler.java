package com.jdc.balance.model.exceptions;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SecurityExceptionHandler implements AccessDeniedHandler, AuthenticationEntryPoint{

	@Autowired
	private HandlerExceptionResolver handlerExceptionResolver;
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		handlerExceptionResolver.resolveException(request, response, null, accessDeniedException);
	}

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		if(authException instanceof InsufficientAuthenticationException) {
			authException = new ApiTokenExpirationException();
		} 
		
		handlerExceptionResolver.resolveException(request, response, null, authException);
	}

}
