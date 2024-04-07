package com.jdc.balance.api.output;

import java.time.LocalDateTime;

import com.jdc.balance.model.EmployeeChanges;
import com.jdc.balance.model.Role;
import com.jdc.balance.model.Status;

public record EmployeeHistoryInfo(
		String name,
		Role role,
		Status status,
		String phone,
		String email,
		EmployeeChanges changes,
		LocalDateTime changeAt,
		String changeBy) {

}
