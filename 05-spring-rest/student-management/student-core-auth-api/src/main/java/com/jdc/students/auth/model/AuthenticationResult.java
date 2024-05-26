package com.jdc.students.auth.model;

import java.time.LocalDateTime;

public record AuthenticationResult(
		String code,
		String fullName,
		String authority,
		String accessToken,
		String refreshToken,
		boolean activated,
		LocalDateTime accessAt
		) {

}
