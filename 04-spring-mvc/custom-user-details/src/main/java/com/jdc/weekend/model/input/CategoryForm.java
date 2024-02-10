package com.jdc.weekend.model.input;

import com.jdc.weekend.model.entity.Category;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CategoryForm {
	
	private int id;

	@NotEmpty(message = "Please enter category name.")
	private String name;
	private String description;
	private boolean deleted;

	public Category entity() {
		var entity = new Category();
		entity.setName(name);
		entity.setDescription(description);
		entity.setDeleted(deleted);
		return entity;
	}
}
