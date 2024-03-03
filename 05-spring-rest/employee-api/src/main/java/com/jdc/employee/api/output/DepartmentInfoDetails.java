package com.jdc.employee.api.output;

import com.jdc.employee.model.entity.Department;

public record DepartmentInfoDetails(
		String code,
		String name,
		String phone,
		String hodCode,
		String hodName,
		String hodPhone,
		String description
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
				entity.getDescription());
	}
}
