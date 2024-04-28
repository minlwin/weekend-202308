package com.jdc.balance.model.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LedgerEntrySeq {
	
	@Id
	@Column(name = "issue_date", nullable = false)
	private LocalDate issueDate;
	@Column(name = "seq_number", nullable = false)
	private int seqNumber;
	
	public LedgerEntryPk next() {
		return new LedgerEntryPk(issueDate, seqNumber ++);
	}	
}
