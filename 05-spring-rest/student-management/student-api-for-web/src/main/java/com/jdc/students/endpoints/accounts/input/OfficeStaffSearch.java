package com.jdc.students.endpoints.accounts.input;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.jdc.students.domain.account.entity.Account_;
import com.jdc.students.domain.account.entity.OfficeStaff;
import com.jdc.students.domain.account.entity.OfficeStaff_;
import com.jdc.students.utils.consts.EmployeeStatus;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record OfficeStaffSearch(
		EmployeeStatus status,
		Boolean activated,
		String name,
		LocalDate from,
		LocalDate to) {
	
	public Predicate [] where(CriteriaBuilder cb, Root<OfficeStaff> root) {
		var list = new ArrayList<Predicate>();
		
		if(null != status) {
			list.add(cb.equal(root.get(OfficeStaff_.status), status));
		}
		
		if(null != activated) {
			list.add(cb.equal(root.get(OfficeStaff_.account).get(Account_.activated), activated));
		}
		
		if(null != from) {
			list.add(cb.greaterThanOrEqualTo(root.get(OfficeStaff_.assignAt), from));
		}
		
		if(null != to) {
			list.add(cb.lessThanOrEqualTo(root.get(OfficeStaff_.assignAt), to));
		}
		
		if(StringUtils.hasLength(name)) {
			var account = root.get(OfficeStaff_.account);
			list.add(cb.equal(cb.lower(account.get(Account_.fullName)), name.toLowerCase().concat("%")));
		}
		
		return list.toArray(size -> new Predicate[size]);
	}

}
