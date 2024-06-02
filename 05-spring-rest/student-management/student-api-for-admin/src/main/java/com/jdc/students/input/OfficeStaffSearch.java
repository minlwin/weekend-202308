package com.jdc.students.input;

import java.time.LocalDate;

import com.jdc.students.utils.consts.EmployeeStatus;

public record OfficeStaffSearch(
		EmployeeStatus status,
		Boolean activated,
		String name,
		LocalDate from,
		LocalDate to) {

}
