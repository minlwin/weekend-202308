package com.jdc.balance.model.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.balance.model.Role;
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
						.authorities(getAuthroties(user))
						.disabled(isDisable(user))
						.build())
				.orElseThrow(() -> new UsernameNotFoundException("There is no user with login id %s.".formatted(username)));
	}
	
	private String getAuthroties(Account account) {
		if(account.getRole() != Role.Admin) {
			var employee = account.getEmployee();
			if(null != employee && employee.getStatus() == Status.Applied) {
				return "Applied";
			}
		}
		
		return account.getRole().name();
	}

	private boolean isDisable(Account user) {
		
		var employee = user.getEmployee();
		
		if(null != employee) {
			return employee.getStatus() == Status.Resigned;
		}
		
		return false;
	}

}
