package com.jdc.balance.api.output;

import java.math.BigDecimal;

import com.jdc.balance.model.entity.LedgerEntryItem;

public record LedgerEntryInfoItem(
		String item,
		int quantity,
		BigDecimal unitPrice) {
	
	public static LedgerEntryInfoItem from(LedgerEntryItem entity) {
		return new LedgerEntryInfoItem(entity.getItem(), entity.getQuantity(), entity.getUnitPrice());
	}

	public BigDecimal getTotal() {
		return unitPrice.multiply(new BigDecimal(quantity));
	}
}
