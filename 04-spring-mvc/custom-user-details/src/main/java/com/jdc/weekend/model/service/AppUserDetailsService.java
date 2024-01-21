package com.jdc.weekend.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jdc.weekend.model.repo.MemberRepo;

@Service
public class AppUserDetailsService implements UserDetailsService{
	
	@Autowired
	private MemberRepo repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repo.findOneByEmail(username)
				.map(member -> User.withUsername(username)
						.authorities(member.getRole().name())
						.password(member.getPassword())
						.disabled(member.isDeleted())
						.build())
				.orElseThrow(() -> new UsernameNotFoundException(username));
	}

}
