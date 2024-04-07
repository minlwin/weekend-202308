package com.jdc.balance.api.input;

import java.math.BigDecimal;

public record LedgerEntryFormItem(
		String item,
		int quantity,
		BigDecimal unitPrice) {

}
