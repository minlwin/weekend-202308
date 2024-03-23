package com.jdc.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import com.jdc.rest.api.model.LoginForm;
import com.jdc.rest.api.model.LoginResult;

@Service
public class LoginService {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private ApiTokenProvider provider;

	public LoginResult login(LoginForm form) {
		
		// Authenticate
		var authentication = authenticationManager.authenticate(form.authentication());
		
		// Generate Token
		var token = provider.generate(authentication);
		
		// Create Login Result
		return new LoginResult("API User", token);
	}

}
