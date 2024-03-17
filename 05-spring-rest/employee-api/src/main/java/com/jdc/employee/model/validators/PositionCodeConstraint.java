package com.jdc.employee.model.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.employee.model.repo.PositionRepo;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class PositionCodeConstraint implements ConstraintValidator<PositionCode, String>{

	@Autowired
	private PositionRepo repo;
	
	@Override
	@Transactional(readOnly = true)
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return repo.countByCode(value) == 0L;
	}

}
