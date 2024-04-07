package com.jdc.balance.api.output;

import java.math.BigDecimal;
import java.util.List;

public record LedgerEntryInfoDetails(
		LedgerEntryInfo info,
		List<LedgerEntryInfoItem> items) {

	public int getCount() {
		return items.stream().mapToInt(a -> a.quantity()).sum();
	}
	
	public BigDecimal getTotal() {
		return items.stream().map(a -> a.getTotal())
				.reduce(BigDecimal.ZERO, (a, b) -> a.add(b));
	}
}
