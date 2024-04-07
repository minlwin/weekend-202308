package com.jdc.balance.model.entity;

import java.time.LocalDateTime;

import com.jdc.balance.model.AbstractEntity;
import com.jdc.balance.model.EmployeeChanges;
import com.jdc.balance.model.Role;
import com.jdc.balance.model.Status;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class EmployeeHistory extends AbstractEntity{

	@EmbeddedId
	private EmployeeHistoryPk id;
	
	@ManyToOne
	@JoinColumn(insertable = false, updatable = false)
	private Employee employee;
	
	@Column(nullable = false)
	private EmployeeChanges changes;
	@Column(nullable = false)
	private LocalDateTime changeAt;
	@Column(nullable = false)
	private String changeBy;
	
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private Role role;
	@Column(nullable = false)
	private Status status;
	@Column(nullable = false)
	private LocalDateTime statusChangeAt;
	@Column(nullable = false)
	private String phone;
	@Column(nullable = false)
	private String email;	

}
