package com.jdc.balance.api.output;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.jdc.balance.model.BalanceType;
import com.jdc.balance.model.entity.Account_;
import com.jdc.balance.model.entity.Category_;
import com.jdc.balance.model.entity.LedgerEntry;
import com.jdc.balance.model.entity.LedgerEntryItem_;
import com.jdc.balance.model.entity.LedgerEntryPk;
import com.jdc.balance.model.entity.LedgerEntryPk_;
import com.jdc.balance.model.entity.LedgerEntry_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;

public record LedgerEntryInfo(
		String id,
		int categoryId,
		String category,
		BalanceType type,
		String issuerId,
		String issuer,
		LocalDate issueAt,
		int items,
		BigDecimal amount,
		String remark) {
	
	public LedgerEntryInfo(
		LocalDate issueDate,
		int seqNum,
		int categoryId,
		String category,
		BalanceType type,
		String issuerId,
		String issuer,
		String remark,
		int items,
		BigDecimal amount) {
		this(new LedgerEntryPk(issueDate, seqNum).getValue(), categoryId, category, type, issuerId, issuer, issueDate, items, amount, remark);
	}

	public static void select(CriteriaBuilder cb, CriteriaQuery<LedgerEntryInfo> cq, Root<LedgerEntry> root) {
		
		var category = root.join(LedgerEntry_.category);
		var account = root.join(LedgerEntry_.account);
		var items = root.join(LedgerEntry_.items, JoinType.LEFT);
		
		cq.multiselect(
			root.get(LedgerEntry_.id).get(LedgerEntryPk_.issueDate),
			root.get(LedgerEntry_.id).get(LedgerEntryPk_.seqNumber),
			category.get(Category_.id),
			category.get(Category_.name),
			category.get(Category_.type),
			account.get(Account_.loginId),
			account.get(Account_.name),
			root.get(LedgerEntry_.remark),
			cb.sum(items.get(LedgerEntryItem_.quantity)),
			cb.sum(cb.prod(items.get(LedgerEntryItem_.quantity), items.get(LedgerEntryItem_.unitPrice)))
		);
		
		cq.groupBy(
			root.get(LedgerEntry_.id).get(LedgerEntryPk_.issueDate),
			root.get(LedgerEntry_.id).get(LedgerEntryPk_.seqNumber),
			category.get(Category_.id),
			category.get(Category_.name),
			category.get(Category_.type),
			account.get(Account_.loginId),
			account.get(Account_.name),
			root.get(LedgerEntry_.remark)
		);
	}

	public static LedgerEntryInfo from(LedgerEntry entity) {
		return new LedgerEntryInfo(
				entity.getId().getValue(), 
				entity.getCategory().getId(), 
				entity.getCategory().getName(), 
				entity.getCategory().getType(),
				entity.getAccount().getLoginId(),
				entity.getAccount().getName(), 
				entity.getId().getIssueDate(),
				entity.getItems().stream().mapToInt(a -> a.getQuantity()).sum(), 
				entity.getItems().stream().map(a -> BigDecimal.valueOf(a.getQuantity()).multiply(a.getUnitPrice()))
					.reduce(BigDecimal.ZERO, (a, b) -> a.add(b)), 
				entity.getRemark());
	}
}
