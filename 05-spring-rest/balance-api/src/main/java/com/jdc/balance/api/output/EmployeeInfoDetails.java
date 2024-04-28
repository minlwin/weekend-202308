package com.jdc.balance.api.output;

import java.util.List;

import com.jdc.balance.model.entity.Employee;

public record EmployeeInfoDetails(
		EmployeeInfo info,
		List<EmployeeHistoryInfo> history
		) {

	public static EmployeeInfoDetails from(Employee entity) {
		return new EmployeeInfoDetails(
				EmployeeInfo.from(entity), 
				entity.getHistories().stream().map(EmployeeHistoryInfo::from).toList());
	}
}
