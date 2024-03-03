package com.jdc.employee.model.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Department {

	@Id
	private String code;
	
	@Column(nullable = false, unique = true)
	private String name;
	
	@ManyToOne
	private Employee headOfDepartment;
	
	@Column(nullable = false)
	private String phone;
	
	private String description;
	
	@OneToMany(mappedBy = "department")
	private List<Employee> employees;
}
