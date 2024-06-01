package com.jdc.students.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.students.auth.model.AccessUserInfo;
import com.jdc.students.auth.model.AuthenticationForm;
import com.jdc.students.auth.model.AuthenticationResult;
import com.jdc.students.service.JwtTokenProvider.TokenType;

@Service
@Transactional(readOnly = true)
public class AppAuthenticationService {
	
	@Autowired
	private JwtTokenProvider provider;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	public AuthenticationResult generate(AuthenticationForm form) {
		var authentication = authenticationManager.authenticate(form.authentication());
		var accessUser = AccessUserInfo.from(authentication);
		return getResult(accessUser);
	}

	public AccessUserInfo validate(String token) {
		return provider.validate(token, TokenType.Access);
	}

	public AuthenticationResult refresh(String token) {
		var accessUser = provider.validate(token, TokenType.Refresh);
		return getResult(accessUser);
	}
	
	private AuthenticationResult getResult(AccessUserInfo accessUser) {
		if(null != accessUser) {
			var accessToken = provider.generateAccessToken(accessUser);
			var refreshToken = provider.generateRefreshToken(accessUser);
			
			return AuthenticationResult.builder()
					.code(accessUser.code())
					.fullName(accessUser.fullName())
					.authority(accessUser.authority())
					.accessToken(accessToken)
					.refreshToken(refreshToken)
					.build();
		}
		return null;
	}

}
