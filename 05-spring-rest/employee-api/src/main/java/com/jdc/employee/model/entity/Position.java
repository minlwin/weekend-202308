package com.jdc.employee.model.entity;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Position {

	@Id
	private String code;
	private String name;
	private BigDecimal basicSalary;
	private BigDecimal otPerHour;
	private String remark;
	
	@OneToMany(mappedBy = "position")
	private List<Employee> employees;
}
