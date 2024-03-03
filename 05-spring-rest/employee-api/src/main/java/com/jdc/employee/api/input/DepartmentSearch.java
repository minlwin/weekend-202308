package com.jdc.employee.api.input;

import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.jdc.employee.model.entity.Department;
import com.jdc.employee.model.entity.Department_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record DepartmentSearch(
		String code,
		String name) {

	public Predicate[] where(CriteriaBuilder cb, Root<Department> root) {
		
		var list = new ArrayList<Predicate>();
		
		if(StringUtils.hasLength(code)) {
			list.add(cb.equal(root.get(Department_.code), code));
		}
		
		if(StringUtils.hasLength(name)) {
			list.add(cb.like(root.get(Department_.name), name.toLowerCase().concat("%")));
		}
		
		return list.toArray(size -> new Predicate[size]);
	}
}
