package com.jdc.balance.api.input;

import jakarta.validation.constraints.NotBlank;

public record ProfileForm(
		@NotBlank(message = "Please enter login id.")
		String loginId,
		@NotBlank(message = "Please enter name.")
		String name,
		@NotBlank(message = "Please enter phone.")
		String phone
		) {

}
