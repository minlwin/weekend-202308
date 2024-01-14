package com.jdc.weekend.model.input;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProfileForm(
		@NotBlank(message = "Please enter user name.")
		String name,
		@NotBlank(message = "Please enter phone number.")
		String phone,
		@NotBlank(message = "Please enter greeting message.")
		String greeting,
		String imageUrl,
		@NotNull(message = "Please select profile image.")
		MultipartFile image) {

}
