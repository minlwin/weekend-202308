package com.jdc.balance.api.input;

import java.time.LocalDate;

import com.jdc.balance.model.BalanceType;

public record LedgerEntrySearch(
		BalanceType type,
		LocalDate from,
		LocalDate to,
		String keyword) {

}
