package com.jdc.balance.api.output;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record BalanceInfo(
		String id,
		int categoryId,
		String category,
		String issuer,
		LocalDateTime issueAt,
		BigDecimal income,
		BigDecimal expense,
		BigDecimal balance,
		String remark) {

}
