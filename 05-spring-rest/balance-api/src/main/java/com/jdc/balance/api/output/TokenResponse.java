package com.jdc.balance.api.output;

import com.jdc.balance.model.Role;

public record TokenResponse(
		String name,
		String loginId,
		Role role,
		String accessToken,
		String refreshToken
		) {

}
