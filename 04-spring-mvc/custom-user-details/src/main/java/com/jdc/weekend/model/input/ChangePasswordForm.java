package com.jdc.weekend.model.input;

import jakarta.validation.constraints.NotBlank;

public record ChangePasswordForm(
		@NotBlank(message = "Please enter old password.")
		String oldPassword,
		@NotBlank(message = "Please enter new password.")
		String newPassword
		) {

}
