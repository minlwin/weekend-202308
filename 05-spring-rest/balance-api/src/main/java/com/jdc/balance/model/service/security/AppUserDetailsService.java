package com.jdc.balance.model.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.balance.model.Status;
import com.jdc.balance.model.entity.Account;
import com.jdc.balance.model.repo.AccountRepo;

@Service
public class AppUserDetailsService implements UserDetailsService{
	
	@Autowired
	private AccountRepo repo;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repo.findOneByLoginId(username)
				.map(user -> User.withUsername(username)
						.password(user.getPassword())
						.authorities(user.getRole().name())
						.disabled(isDisable(user))
						.build())
				.orElseThrow(() -> new UsernameNotFoundException("There is no user with login id %s.".formatted(username)));
	}

	private boolean isDisable(Account user) {
		
		var employee = user.getEmployee();
		
		if(null != employee) {
			return employee.getStatus() == Status.Resigned;
		}
		
		return false;
	}

}
