package com.jdc.students.domain.account.entity;

import java.time.LocalDate;

import com.jdc.students.utils.consts.EmployeeStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Teacher extends LoginUser{
	
	@Column(nullable = false)
	private EmployeeStatus status;
	
	@Column(nullable = false)
	private LocalDate assignAt;
	private LocalDate provationAt;
	private LocalDate resignAt;

}
