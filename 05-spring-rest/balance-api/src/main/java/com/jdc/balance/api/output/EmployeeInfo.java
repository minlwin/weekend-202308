package com.jdc.balance.api.output;

import com.jdc.balance.model.Role;
import com.jdc.balance.model.Status;
import com.jdc.balance.model.entity.Account_;
import com.jdc.balance.model.entity.Employee;
import com.jdc.balance.model.entity.Employee_;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public record EmployeeInfo(
		int id,
		String name,
		Role role,
		Status status,
		String phone,
		String email
		) {
	
	public static void select(CriteriaQuery<EmployeeInfo> cq, Root<Employee> root) {
		var account = root.join(Employee_.account);
		cq.multiselect(
			root.get(Employee_.id),
			account.get(Account_.name),
			account.get(Account_.role),
			root.get(Employee_.status),
			root.get(Employee_.phone),
			root.get(Employee_.email)
		);
	}

	public static EmployeeInfo from(Employee entity) {
		return new EmployeeInfo(
				entity.getId(), 
				entity.getAccount().getName(), 
				entity.getAccount().getRole(), 
				entity.getStatus(), 
				entity.getPhone(), 
				entity.getEmail());
	}
}
