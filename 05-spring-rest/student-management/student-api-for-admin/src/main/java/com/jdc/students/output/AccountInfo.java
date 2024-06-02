package com.jdc.students.output;

import com.jdc.students.domain.account.entity.Account;
import com.jdc.students.domain.account.entity.Account.Role;
import com.jdc.students.domain.account.entity.Account_;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public record AccountInfo(
		String code,
		String name,
		Role role,
		boolean activated) {
	
	public static void select(CriteriaQuery<AccountInfo> cq, Root<Account> root) {
		cq.multiselect(
			root.get(Account_.code),
			root.get(Account_.fullName),
			root.get(Account_.role),
			root.get(Account_.activated)
		);
	}
}
