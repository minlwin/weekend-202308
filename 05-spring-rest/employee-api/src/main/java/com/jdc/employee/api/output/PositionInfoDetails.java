package com.jdc.employee.api.output;

import java.math.BigDecimal;
import java.util.List;

import com.jdc.employee.model.entity.Position;

public record PositionInfoDetails(
		String code,
		String name,
		BigDecimal basicSalary,
		BigDecimal otPerHour,
		String remark,
		List<EmployeeInfo> employees) {

	public static PositionInfoDetails from(Position entity) {
		return new PositionInfoDetails(
				entity.getCode(), 
				entity.getName(), 
				entity.getBasicSalary(), 
				entity.getOtPerHour(), 
				entity.getRemark(), 
				entity.getEmployees().stream().map(EmployeeInfo::from).toList());
	}
}
