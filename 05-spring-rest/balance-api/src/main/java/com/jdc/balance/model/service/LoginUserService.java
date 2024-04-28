package com.jdc.balance.model.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.balance.model.LoginUser;
import com.jdc.balance.model.entity.Account;
import com.jdc.balance.model.repo.AccountRepo;

@Service
public class LoginUserService {
	
	@Autowired
	private AccountRepo repo;

	@Transactional(readOnly = true)
	public Optional<LoginUser> getLoginUser() {
		return Optional.ofNullable(SecurityContextHolder.getContext())
			.map(context -> context.getAuthentication())
			.filter(auth -> !(auth instanceof AnonymousAuthenticationToken))
			.filter(auth -> auth.isAuthenticated())
			.map(auth -> auth.getName())
			.flatMap(username -> repo.findOneByLoginId(username))
			.map(LoginUser::from);
	}
	
	@Transactional(readOnly = true)
	public Optional<Account> getLoginAccount() {
		return Optional.ofNullable(SecurityContextHolder.getContext())
				.map(context -> context.getAuthentication())
				.filter(auth -> !(auth instanceof AnonymousAuthenticationToken))
				.filter(auth -> auth.isAuthenticated())
				.map(auth -> auth.getName())
				.flatMap(username -> repo.findOneByLoginId(username));		
	}
	
	public Optional<String> getLoginId() {
		return Optional.ofNullable(SecurityContextHolder.getContext())
				.map(context -> context.getAuthentication())
				.filter(auth -> !(auth instanceof AnonymousAuthenticationToken))
				.filter(auth -> auth.isAuthenticated())
				.map(auth -> auth.getName());
	}
}
