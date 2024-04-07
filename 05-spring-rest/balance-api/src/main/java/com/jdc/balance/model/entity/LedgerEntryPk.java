package com.jdc.balance.model.entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class LedgerEntryPk implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "issue_date")
	private LocalDate issueDate;
	@Column(name = "seq_number")
	private int seqNumber;
}
