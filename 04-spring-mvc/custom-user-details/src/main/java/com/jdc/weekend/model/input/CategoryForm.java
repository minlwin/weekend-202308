package com.jdc.weekend.model.input;

import jakarta.validation.constraints.NotEmpty;

public record CategoryForm(
		@NotEmpty(message = "Please enter category name.")
		String name,
		boolean deleted
		) {

}
