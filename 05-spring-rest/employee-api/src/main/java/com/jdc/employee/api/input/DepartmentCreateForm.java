package com.jdc.employee.api.input;

import com.jdc.employee.model.entity.Department;

import jakarta.validation.constraints.NotEmpty;

public record DepartmentCreateForm(

		@NotEmpty(message = "Please enter department code.")
		String code,
		@NotEmpty(message = "Please enter department name.")
		String name,
		@NotEmpty(message = "Please enter phone number.")
		String phone,
		String description) {

	public Department entity() {
		var entity = new Department();
		entity.setCode(code);
		entity.setName(name);
		entity.setPhone(phone);
		entity.setDescription(description);
		return entity;
	}
}
