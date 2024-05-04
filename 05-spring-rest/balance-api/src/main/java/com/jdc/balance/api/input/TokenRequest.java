package com.jdc.balance.api.input;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public record TokenRequest(
		String loginId,
		String password) {

	public UsernamePasswordAuthenticationToken authenticationToken() {
		return UsernamePasswordAuthenticationToken.unauthenticated(loginId, password);
	}
}
