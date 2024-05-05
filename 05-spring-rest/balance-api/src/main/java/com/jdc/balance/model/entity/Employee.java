package com.jdc.balance.model.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.jdc.balance.model.AbstractEntity;
import com.jdc.balance.model.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Employee extends AbstractEntity{

	@Id
	private int id;
	
	@MapsId
	@OneToOne(optional = false)
	private Account account;
	
	@Column(nullable = false)
	private Status status;
	
	@Column(nullable = false)
	private LocalDateTime statusChangeAt;
	@Column(nullable = false)
	private String phone;
	@Column(nullable = false, unique = true)
	private String email;	
	
	@OneToMany(mappedBy = "employee")
	private List<EmployeeHistory> histories = new ArrayList<>();
}
