package com.jdc.employee.api.input;

import jakarta.validation.constraints.NotEmpty;

public record DepartmentUpdateForm(
		@NotEmpty(message = "Please enter department name.")
		String name,
		@NotEmpty(message = "Please enter phone number.")
		String phone,
		String description) {

}
