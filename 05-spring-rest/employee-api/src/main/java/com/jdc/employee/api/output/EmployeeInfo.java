package com.jdc.employee.api.output;

import com.jdc.employee.model.entity.Employee;

public record EmployeeInfo(
		String code, 
		String name,
		String department,
		String position,
		String phone, 
		String email) {

	public static EmployeeInfo from(Employee entity) {
		return new EmployeeInfo(
				entity.getCode(), 
				entity.getName(), 
				entity.getDepartment().getCode(), 
				entity.getPosition().getCode(), 
				entity.getPhone(), 
				entity.getEmail());
	}
}
