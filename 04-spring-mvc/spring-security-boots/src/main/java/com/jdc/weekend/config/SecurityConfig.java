package com.jdc.weekend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(req -> {
			req.requestMatchers("/", "/home").permitAll();
			req.requestMatchers("/admin/**").hasAuthority("Admin");
			req.requestMatchers("/member/**").hasAnyAuthority("Admin", "Member");
		});
		
		http.formLogin(form -> {});
		http.logout(logout -> {});
		
		return http.build();
	}

	@Bean
	InMemoryUserDetailsManager userDetailsManager() {
		return new InMemoryUserDetailsManager(
			User.withUsername("admin").authorities("Admin").password("{noop}admin").build(),	
			User.withUsername("member").authorities("Member").password("{noop}member").build());
	}
}
