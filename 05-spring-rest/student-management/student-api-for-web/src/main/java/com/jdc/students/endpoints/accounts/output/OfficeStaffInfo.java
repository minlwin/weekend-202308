package com.jdc.students.endpoints.accounts.output;

import java.time.LocalDate;

import com.jdc.students.utils.consts.EmployeeStatus;

public record OfficeStaffInfo(
		String code,
		String name,
		EmployeeStatus status,
		boolean activated,
		String phone,
		String email,
		LocalDate assignAt,
		LocalDate provationAt,
		LocalDate resignAt) {

}
