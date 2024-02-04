package com.jdc.weekend.model.input;

import com.jdc.weekend.model.entity.Category;

import jakarta.validation.constraints.NotEmpty;

public record CategoryForm(
		@NotEmpty(message = "Please enter category name.")
		String name,
		String description,
		boolean deleted
		) {

	public Category entity() {
		var entity = new Category();
		entity.setName(name);
		entity.setDescription(description);
		entity.setDeleted(deleted);
		return entity;
	}
}
