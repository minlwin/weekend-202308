package com.jdc.weekend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

import com.jdc.weekend.model.entity.Member;
import com.jdc.weekend.model.entity.Member.Role;
import com.jdc.weekend.model.repo.MemberRepo;

@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {
	
	@Autowired
	private MemberRepo memberRepo;
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityContextRepository securityContextRepository() {
		return new HttpSessionSecurityContextRepository();
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	SecurityFilterChain http(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(request -> {
			request.requestMatchers("/", "/js/**", "/public/**", "/login").permitAll();
			request.requestMatchers("/admin/**").hasAuthority("Admin");
			request.requestMatchers("/member/**").hasAuthority("Member");			
			request.anyRequest().authenticated();
		});
		
		http.formLogin(login -> {
			login.loginPage("/login");
			login.usernameParameter("loginId");
			login.passwordParameter("pass");
		});
		
		return http.build();
	}
	
	@Bean
	ApplicationRunner applicationRunner(PasswordEncoder passwordEncoder) {
		return args -> {
			if(memberRepo.count() == 0) {
				var admin = new Member();
				admin.setName("Admin User");
				admin.setEmail("admin@gmail.com");
				admin.setPassword(passwordEncoder.encode("Admin"));
				admin.setRole(Role.Admin);
				memberRepo.save(admin);
			}
		};
	}
}
