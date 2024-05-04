package com.jdc.balance.model.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.balance.model.Role;
import com.jdc.balance.model.entity.Account;
import com.jdc.balance.model.repo.AccountRepo;

@Service
public class AdminUserInitializer {
	
	@Autowired
	private AccountRepo repo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Value("${app.user.admin.name}")
	private String name;
	@Value("${app.user.admin.loginid}")
	private String loginId;
	@Value("${app.user.admin.password}")
	private String password;

	@Transactional
	public void initilizeAdmin() {
		if(repo.count() == 0L) {
			var admin = new Account();
			admin.setName(name);
			admin.setLoginId(loginId);
			admin.setRole(Role.Admin);
			admin.setPassword(passwordEncoder.encode(password));
			repo.save(admin);
		}
	}
}
