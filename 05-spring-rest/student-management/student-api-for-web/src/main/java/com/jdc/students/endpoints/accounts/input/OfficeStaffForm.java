package com.jdc.students.endpoints.accounts.input;

import java.time.LocalDate;

import com.jdc.students.utils.consts.EmployeeStatus;

public record OfficeStaffForm(
		String name,
		String phone,
		String email,
		EmployeeStatus status,
		LocalDate statusChageAt) {

}
