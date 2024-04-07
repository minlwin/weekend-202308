package com.jdc.balance.model.entity;

import com.jdc.balance.model.AbstractEntity;
import com.jdc.balance.model.BalanceType;

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
public class Category extends AbstractEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private BalanceType type;
	@Column(nullable = false)
	private String name;
	private String description;
}
