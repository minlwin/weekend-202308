package com.jdc.students.input;

import java.time.LocalDate;

import com.jdc.students.utils.consts.EmployeeStatus;

public record OfficeStaffForm(
		String name,
		String phone,
		String email,
		EmployeeStatus status,
		LocalDate statusChageAt) {

}
