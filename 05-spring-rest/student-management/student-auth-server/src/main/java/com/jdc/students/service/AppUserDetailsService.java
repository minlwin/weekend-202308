package com.jdc.students.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.students.domain.account.entity.Account;
import com.jdc.students.domain.account.entity.OfficeStaff;
import com.jdc.students.domain.account.entity.Student;
import com.jdc.students.domain.account.entity.Teacher;
import com.jdc.students.domain.account.repo.AccountRepo;

@Service
public class AppUserDetailsService implements UserDetailsService{
	
	@Autowired
	private AccountRepo repo;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repo.findById(username)
			.map(a -> switch(a.getLoginUser()) {
				case OfficeStaff user -> getUserDetails(user);
				case Teacher user -> getUserDetails(user);
				case Student user -> getUserDetails(user);
				case null -> getUserDetails(a);
				default -> throw new IllegalArgumentException();
			}).orElseThrow(() -> new UsernameNotFoundException(username));
	}

	private UserDetails getUserDetails(Account a) {
		// TODO Auto-generated method stub
		return null;
	}

	private UserDetails getUserDetails(OfficeStaff user) {
		// TODO Auto-generated method stub
		return null;
	}

	private UserDetails getUserDetails(Student user) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private UserDetails getUserDetails(Teacher user) {
		// TODO Auto-generated method stub
		return null;
	}	
}
