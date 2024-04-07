package com.jdc.balance.api.input;

import com.jdc.balance.model.Role;
import com.jdc.balance.model.Status;

public record EmployeeSearch(
		Role role,
		Status status,
		String keyword) {

}
