package com.jdc.employee.api.output;

import java.util.List;

import com.jdc.employee.model.entity.Department;

public record DepartmentInfoDetails(
		String code,
		String name,
		String phone,
		String hodCode,
		String hodName,
		String hodPhone,
		String description,
		List<EmployeeInfo> employees
		) {

	public static DepartmentInfoDetails from(Department entity) {
		var hod = entity.getHeadOfDepartment();
		return new DepartmentInfoDetails(
				entity.getCode(), 
				entity.getName(), 
				entity.getPhone(), 
				null == hod ? null : hod.getCode(), 
				null == hod ? null : hod.getName(), 
				null == hod ? null : hod.getPhone(), 
				entity.getDescription(),
				entity.getEmployees().stream().map(EmployeeInfo::from).toList());
	}
}
