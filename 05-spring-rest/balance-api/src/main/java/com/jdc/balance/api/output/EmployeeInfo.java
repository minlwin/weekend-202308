package com.jdc.balance.api.output;

import com.jdc.balance.model.Role;
import com.jdc.balance.model.Status;

public record EmployeeInfo(
		int id,
		String name,
		Role role,
		Status status,
		String phone,
		String email
		) {

}
