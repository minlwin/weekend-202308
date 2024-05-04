package com.jdc.balance.api.input;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import jakarta.validation.constraints.NotBlank;

public record TokenRequest(
		@NotBlank(message = "Please enter login id.")
		String loginId,
		@NotBlank(message = "Please enter password.")
		String password) {

	public UsernamePasswordAuthenticationToken authenticationToken() {
		return UsernamePasswordAuthenticationToken.unauthenticated(loginId, password);
	}
}
