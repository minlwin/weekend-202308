package com.jdc.balance.model.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class EmployeeHistoryPk implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "employee_id")
	private int employeeId;
	@Column(name = "seq_number")
	private int seqNumber;
}
