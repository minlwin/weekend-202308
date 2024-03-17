package com.jdc.employee.model.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.employee.model.repo.DepartmentRepo;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class DepartmentCodeConstraint implements ConstraintValidator<DepartmentCode, String>{

	@Autowired
	private DepartmentRepo repo;
	
	@Override
	@Transactional(readOnly = true)
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return repo.countByCode(value) == 0L;
	}

}
