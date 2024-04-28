package com.jdc.balance.api.input;

import java.time.LocalDate;
import java.util.List;

import com.jdc.balance.model.entity.LedgerEntry;
import com.jdc.balance.model.entity.LedgerEntryItem;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record LedgerEntryForm(
		@NotNull(message = "Please enter issue at.")
		LocalDate issueAt,
		@NotNull(message = "Please select category")
		Integer category,
		String remark,
		@NotEmpty(message = "Please enter entry items.")
		List<@Valid LedgerEntryFormItem> items) {

	public LedgerEntry entity() {
		var entity = new LedgerEntry();
		entity.setRemark(remark);
		entity.setItems(getItemsForDb());
		return entity;
	}
	
	public List<LedgerEntryItem> getItemsForDb() {
		return items.stream().map(a -> a.embddable()).toList();
	}

}
