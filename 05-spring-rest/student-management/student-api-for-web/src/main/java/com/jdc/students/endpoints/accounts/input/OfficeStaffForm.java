package com.jdc.students.endpoints.accounts.input;

import java.time.LocalDate;

import com.jdc.students.domain.account.entity.Account;
import com.jdc.students.domain.account.entity.OfficeStaff;
import com.jdc.students.domain.account.entity.Account.Role;
import com.jdc.students.utils.consts.EmployeeStatus;

public record OfficeStaffForm(
		String name,
		String phone,
		String email,
		EmployeeStatus status,
		LocalDate statusChageAt) {

	public OfficeStaff entity() { 
		var entity = new OfficeStaff();
		
		var account = new Account();
		account.setFullName(name);
		account.setRole(Role.Office);

		entity.setAccount(account);
		entity.setEmail(email);
		entity.setPhone(phone);
		entity.setStatus(status);
		
		switch(status) {
		case PreProvation -> entity.setAssignAt(statusChageAt);
		case Provation -> entity.setProvationAt(statusChageAt);
		case Resigned -> entity.setResignAt(statusChageAt);
		}
		
		return entity;
	}
}
