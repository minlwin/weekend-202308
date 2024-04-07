package com.jdc.balance.api.input;

import com.jdc.balance.model.Status;

public record EmployeeStatusForm(
		Status status,
		String reason) {

}
