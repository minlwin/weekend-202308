package com.jdc.students.domain.account.entity;

import java.time.LocalDate;

import com.jdc.students.utils.consts.EmployeeStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "OFFICE_STAFF")
@EqualsAndHashCode(callSuper = false)
public class OfficeStaff extends LoginUser{

	@Column(nullable = false)
	private EmployeeStatus status;
	
	@Column(nullable = false)
	private LocalDate assignAt;
	private LocalDate provationAt;
	private LocalDate resignAt;
	
	
}
