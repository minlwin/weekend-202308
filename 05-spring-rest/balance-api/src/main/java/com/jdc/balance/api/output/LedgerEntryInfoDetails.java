package com.jdc.balance.api.output;

import java.math.BigDecimal;
import java.util.List;

import com.jdc.balance.model.entity.LedgerEntry;

public record LedgerEntryInfoDetails(
		LedgerEntryInfo info,
		List<LedgerEntryInfoItem> items) {
	
	public static LedgerEntryInfoDetails from(LedgerEntry entity) {
		return new LedgerEntryInfoDetails(
				LedgerEntryInfo.from(entity), 
				entity.getItems().stream().map(LedgerEntryInfoItem::from).toList());
	}

	public int getCount() {
		return info.items();
	}
	
	public BigDecimal getTotal() {
		return info.amount();
	}
}
