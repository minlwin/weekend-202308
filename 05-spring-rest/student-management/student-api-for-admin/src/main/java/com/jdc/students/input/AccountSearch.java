package com.jdc.students.input;

import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.jdc.students.domain.account.entity.Account;
import com.jdc.students.domain.account.entity.Account_;
import com.jdc.students.domain.account.entity.Account.Role;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record AccountSearch(
		Role role,
		String name,
		Boolean activated
		) {

	public Predicate[] where(CriteriaBuilder cb, Root<Account> root) {
		var list = new ArrayList<Predicate>();
		
		if(null != role) {
			list.add(cb.equal(root.get(Account_.role), role));
		}
		
		if(StringUtils.hasLength(name)) {
			list.add(cb.like(cb.lower(root.get(Account_.fullName)), name.toLowerCase().concat("%")));
		}
		
		if(null != activated) {
			list.add(cb.equal(root.get(Account_.activated), activated));
		}
		
		return list.toArray(size -> new Predicate[size]);
	}
}
