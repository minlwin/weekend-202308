package com.jdc.balance.model.entity;

import java.math.BigDecimal;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class LedgerEntryItem {

	private String item;
	private int quantity;
	private BigDecimal unitPrice;
}
