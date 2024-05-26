package com.jdc.students.auth.model;

import jakarta.validation.constraints.NotEmpty;

public record AuthenticationForm(
		@NotEmpty(message = "Please enter login id.")
		String code,
		@NotEmpty(message = "Please enter password.")
		String password) {

}
