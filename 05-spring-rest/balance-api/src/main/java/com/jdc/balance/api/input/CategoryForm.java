package com.jdc.balance.api.input;

import com.jdc.balance.model.BalanceType;
import com.jdc.balance.model.entity.Category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryForm(
		@NotNull(message = "Please select balance type.")
		BalanceType type,
		@NotBlank(message = "Please enter category name.")
		String name,
		String description) {

	public Category getEntity() {
		var entity = new Category();
		entity.setName(name);
		entity.setType(type);
		entity.setDescription(description);
		return entity;
	}

}
