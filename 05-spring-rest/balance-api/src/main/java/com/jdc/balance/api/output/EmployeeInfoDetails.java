package com.jdc.balance.api.output;

import java.util.List;

public record EmployeeInfoDetails(
		EmployeeInfo info,
		List<EmployeeHistoryInfo> history
		) {

}
