package com.jdc.weekend.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.weekend.model.entity.Member;
import com.jdc.weekend.model.entity.Member.Role;
import com.jdc.weekend.model.input.SignUpForm;
import com.jdc.weekend.model.repo.MemberRepo;

@Service
public class SignUpService {
	
	@Autowired
	private MemberRepo repo;
	@Autowired
	private PasswordEncoder encoder;
	
	@Transactional
	public void signUp(SignUpForm form) {
		var member = new Member();
		member.setName(form.getName());
		member.setPassword(encoder.encode(form.getPassword()));
		member.setEmail(form.getEmail());
		member.setRole(Role.Member);
		
		repo.save(member);
	}
}
