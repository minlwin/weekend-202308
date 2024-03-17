package com.jdc.employee.api.output;

import java.math.BigDecimal;

import com.jdc.employee.model.entity.Employee_;
import com.jdc.employee.model.entity.Position;
import com.jdc.employee.model.entity.Position_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;

public record PositionInfo(
		String code,
		String name,
		BigDecimal basicSalary,
		BigDecimal otPerHour,
		String remark,
		long employees
		) {

	public static void select(CriteriaBuilder cb, CriteriaQuery<PositionInfo> cq, Root<Position> root) {
		
		var employees = root.join(Position_.employees, JoinType.LEFT);
		
		cq.multiselect(
			root.get(Position_.code),
			root.get(Position_.name),
			root.get(Position_.basicSalary),
			root.get(Position_.otPerHour),
			root.get(Position_.remark),
			cb.count(employees.get(Employee_.code)));
		
		cq.groupBy(
			root.get(Position_.code),
			root.get(Position_.name),
			root.get(Position_.basicSalary),
			root.get(Position_.otPerHour),
			root.get(Position_.remark));
		
		cq.orderBy(cb.asc(root.get(Position_.code)));
	}
}
