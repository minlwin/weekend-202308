package com.jdc.balance.api.output;

public record TokenResponse(
		String name,
		String loginId,
		String role,
		String accessToken,
		String refreshToken
		) {

}
