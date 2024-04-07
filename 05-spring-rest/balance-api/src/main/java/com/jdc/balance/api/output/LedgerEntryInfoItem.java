package com.jdc.balance.api.output;

import java.math.BigDecimal;

public record LedgerEntryInfoItem(
		String item,
		int quantity,
		BigDecimal unitPrice) {

	public BigDecimal getTotal() {
		return unitPrice.multiply(new BigDecimal(quantity));
	}
}
