package com.jdc.balance.api.input;

import com.jdc.balance.model.Role;

public record EmployeeForm(
		String name,
		Role role,
		String phone,
		String email) {

}
