package com.jdc.balance.api.input;

import java.time.LocalDate;
import java.util.List;

public record LedgerEntryForm(
		LocalDate issueAt,
		int category,
		String remark,
		List<LedgerEntryFormItem> items) {

}
