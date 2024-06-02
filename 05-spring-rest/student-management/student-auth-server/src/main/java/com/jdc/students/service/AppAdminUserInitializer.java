package com.jdc.students.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.students.domain.account.AccountCodeGenerator;
import com.jdc.students.domain.account.entity.Account;
import com.jdc.students.domain.account.entity.Account.Role;
import com.jdc.students.domain.account.repo.AccountRepo;

@Service
public class AppAdminUserInitializer {
	
	@Autowired
	private AccountRepo repo;
	@Autowired
	private AccountCodeGenerator codeGenerator;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	@EventListener(classes = ContextRefreshedEvent.class)
	public void initializeAdmin() {
		if(repo.count() == 0L) {
			var admin = new Account();
			admin.setActivated(true);
			admin.setFullName("Admin User");
			admin.setCode(codeGenerator.next(Role.Admin));
			admin.setPassword(passwordEncoder.encode("admin"));
			admin.setRole(Role.Admin);
			
			repo.save(admin);
		}
	}
}
