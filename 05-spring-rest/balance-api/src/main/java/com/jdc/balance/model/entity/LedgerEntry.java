package com.jdc.balance.model.entity;

import java.util.List;

import com.jdc.balance.model.AbstractEntity;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class LedgerEntry extends AbstractEntity{

	@EmbeddedId
	private LedgerEntryPk id;
	
	@ManyToOne(optional = false)
	private Account account;

	@ManyToOne(optional = false)
	private Category category;
	
	@Column(nullable = false)
	private String remark;

	@ElementCollection
	private List<LedgerEntryItem> items;
}
