package com.jdc.balance.api.input;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.jdc.balance.model.BalanceType;
import com.jdc.balance.model.entity.Account_;
import com.jdc.balance.model.entity.Category_;
import com.jdc.balance.model.entity.LedgerEntry;
import com.jdc.balance.model.entity.LedgerEntryPk_;
import com.jdc.balance.model.entity.LedgerEntry_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record LedgerEntrySearch(
		String loginId,
		BalanceType type,
		LocalDate from,
		LocalDate to,
		String keyword) {

	public Predicate[] where(CriteriaBuilder cb, Root<LedgerEntry> root) {
		var list = new ArrayList<Predicate>();
		
		if(StringUtils.hasLength(loginId)) {
			list.add(cb.equal(root.get(LedgerEntry_.account).get(Account_.loginId), loginId));
		}
		
		if(null != type) {
			list.add(cb.equal(root.get(LedgerEntry_.category).get(Category_.type), type));
		}
		
		if(null != from) {
			list.add(cb.greaterThanOrEqualTo(root.get(LedgerEntry_.id).get(LedgerEntryPk_.issueDate), from));
		}

		if(null != to) {
			list.add(cb.lessThanOrEqualTo(root.get(LedgerEntry_.id).get(LedgerEntryPk_.issueDate), to));
		}
		
		if(StringUtils.hasLength(keyword)) {
			list.add(cb.like(cb.lower(root.get(LedgerEntry_.remark)), "%%%s%%".formatted(keyword)));
		}

		return list.toArray(size -> new Predicate[size]);
	}
}
