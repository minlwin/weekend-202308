package com.jdc.students.endpoints.accounts.input;

import java.time.LocalDate;

import com.jdc.students.utils.consts.EmployeeStatus;

public record OfficeStaffSearch(
		EmployeeStatus status,
		Boolean activated,
		String name,
		LocalDate from,
		LocalDate to) {

}
