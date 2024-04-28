package com.jdc.balance.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class LedgerEntryPk implements Serializable{

	private static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("yyyyMMdd");

	private static final long serialVersionUID = 1L;
	
	@Column(name = "issue_date")
	private LocalDate issueDate;
	@Column(name = "seq_number")
	private int seqNumber;
	
	public String getValue() {
		return "%s-%04d".formatted(issueDate.format(DF), seqNumber);
	}
	
	public static LedgerEntryPk parse(String value) {
		var array = value.split("-");
		return new LedgerEntryPk(LocalDate.parse(array[0], DF), Integer.parseInt(array[1]));
	}
}
