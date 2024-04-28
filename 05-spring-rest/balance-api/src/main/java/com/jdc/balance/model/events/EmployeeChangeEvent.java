package com.jdc.balance.model.events;

import com.jdc.balance.model.EmployeeChanges;

public record EmployeeChangeEvent(
		EmployeeChanges changes,
		int employeeId) {

}
