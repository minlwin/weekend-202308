package com.jdc.students.auth.model;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import jakarta.validation.constraints.NotEmpty;

public record AuthenticationForm(
		@NotEmpty(message = "Please enter login id.")
		String code,
		@NotEmpty(message = "Please enter password.")
		String password) {

	public Authentication authentication() {
		return UsernamePasswordAuthenticationToken.unauthenticated(code, password);
	}

}
