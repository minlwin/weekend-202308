package com.jdc.balance.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.jdc.balance.model.AbstractEntity;
import com.jdc.balance.model.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Employee extends AbstractEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne
	private Account account;
	
	@Column(nullable = false)
	private Status status;
	@Column(nullable = false)
	private LocalDateTime statusChangeAt;
	@Column(nullable = false)
	private String phone;
	@Column(nullable = false)
	private String email;	
}
