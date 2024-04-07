package com.jdc.balance.model.entity;

import com.jdc.balance.model.AbstractEntity;
import com.jdc.balance.model.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Account extends AbstractEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false, unique = true)
	private String loginId;
	@Column(nullable = false)
	private Role role;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String password;
}
