package com.jdc.balance.api.input;

import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.jdc.balance.model.Role;
import com.jdc.balance.model.Status;
import com.jdc.balance.model.entity.Account_;
import com.jdc.balance.model.entity.Employee;
import com.jdc.balance.model.entity.Employee_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record EmployeeSearch(
		Role role,
		Status status,
		String keyword) {

	public Predicate[] where(CriteriaBuilder cb, Root<Employee> root) {
		var list = new ArrayList<Predicate>();
		
		var account = root.join(Employee_.account);
		
		if(null != role) {
			list.add(cb.equal(account.get(Account_.role), role));
		}
		
		if(null != status) {
			list.add(cb.equal(root.get(Employee_.status), status));
		}
		
		if(StringUtils.hasLength(keyword)) {
			list.add(cb.like(cb.lower(account.get(Account_.name)), keyword.toLowerCase().concat("%")));
		}
		
		return list.toArray(size -> new Predicate[size]);
	}
}
