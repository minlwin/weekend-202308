package com.jdc.weekend.model.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.jdc.weekend.model.repo.MemberRepo;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class UniqueLoginIdConstraint implements ConstraintValidator<UniqueLoginId, String>{

	@Autowired
	private MemberRepo repo;
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		var authentication = SecurityContextHolder.getContext().getAuthentication();
		if(!(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated()) {
			return repo.findOneByEmail(value)
					.filter(a -> !a.getEmail().equals(authentication.getName()))
					.isEmpty();
		}
		
		return repo.findOneByEmail(value).isEmpty();
	}
}
