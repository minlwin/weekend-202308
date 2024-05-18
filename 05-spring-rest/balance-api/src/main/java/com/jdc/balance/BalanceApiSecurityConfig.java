package com.jdc.balance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

import com.jdc.balance.model.exceptions.SecurityExceptionHandler;
import com.jdc.balance.model.service.security.JwtTokenFilter;

@Configuration
@EnableMethodSecurity
@PropertySource("classpath:/token.properties")
public class BalanceApiSecurityConfig {
	
	@Autowired
	private JwtTokenFilter jwtTokenFilter;
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf(csrf -> csrf.disable());
		http.cors(cors -> {});
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		http.authorizeHttpRequests(request -> {
			request.requestMatchers("/token/**", "/swagger-ui/**", "/api/v1/auth/**", "/v3/api-docs/**", "/fabicon.ico").permitAll();
			request.anyRequest().fullyAuthenticated();
		});
		
		http.addFilterBefore(jwtTokenFilter, AuthorizationFilter.class);
		
		http.exceptionHandling(exceptions -> {
			exceptions.accessDeniedHandler(securityExceptionHandler());
			exceptions.authenticationEntryPoint(securityExceptionHandler());
		});
		
		return http.build();
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	@Bean
	SecurityExceptionHandler securityExceptionHandler() {
		return new SecurityExceptionHandler();
	}
}
