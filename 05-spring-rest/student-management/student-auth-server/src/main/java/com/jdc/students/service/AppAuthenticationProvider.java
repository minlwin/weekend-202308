package com.jdc.students.service;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppAuthenticationProvider extends DaoAuthenticationProvider{

	public AppAuthenticationProvider(PasswordEncoder passwordEncoder, AppUserDetailsService userDetailsService) {
		super(passwordEncoder);
		setUserDetailsService(userDetailsService);
		setHideUserNotFoundExceptions(false);
	}
}
