package com.jdc.balance.api.input;

import java.math.BigDecimal;

import com.jdc.balance.model.entity.LedgerEntryItem;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LedgerEntryFormItem(
		@NotBlank(message = "Please enter item name.")
		String item,
		@NotNull(message = "Please enter quantity.")
		Integer quantity,
		@NotNull(message = "Please enter unit price.")
		BigDecimal unitPrice) {

	public LedgerEntryItem embddable() {
		var dto = new LedgerEntryItem();
		dto.setItem(item);
		dto.setQuantity(quantity);
		dto.setUnitPrice(unitPrice);
		return dto;
	}
}
