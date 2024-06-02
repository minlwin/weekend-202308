package com.jdc.students.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

import com.jdc.students.auth.AccessTokenFilter;
import com.jdc.students.exceptions.SecurityExceptionHandler;

@Configuration
public class WebApiSecurityConfig {

	@Autowired
	private SecurityExceptionHandler securityExceptionHandler;
	@Autowired
	private AccessTokenFilter accessTokenFilter;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf(csrf -> csrf.disable());
		http.cors(cors -> {});
		
		http.authorizeHttpRequests(request -> {
			request.requestMatchers("/public/**").permitAll();
			request.requestMatchers("/admin/**").hasAuthority("Admin");
			request.requestMatchers("/teacher/**").hasAuthority("Teacher");
			request.requestMatchers("/office/**").hasAuthority("Office");
			request.requestMatchers("/student/**").hasAuthority("Student");
			request.anyRequest().denyAll();
		});
		
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		http.exceptionHandling(exception -> {
			exception.accessDeniedHandler(securityExceptionHandler);
			exception.authenticationEntryPoint(securityExceptionHandler);
		});
		
		http.addFilterBefore(accessTokenFilter, AuthorizationFilter.class);
		
		return http.build();
	}
}
