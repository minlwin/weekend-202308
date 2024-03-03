package com.jdc.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.employee.model.entity.EmployeeCode;
import com.jdc.employee.model.repo.EmployeeCodeRepo;

@Service
public class EmployeeCodeGenerator {
	
	@Autowired
	private EmployeeCodeRepo repo;

	@Transactional
	public String next(String departmentCode) {
		
		var employeeCode = repo.findById(departmentCode).orElseGet(() -> {
			var entity = new EmployeeCode();
			entity.setDepartmentCode(departmentCode);
			return repo.save(entity);
		});
		
		var code = employeeCode.next();
		
		return "%s%04d".formatted(code);
	}
}
