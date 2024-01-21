package com.jdc.weekend.model.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignUpForm {

	@NotBlank(message = "Please enter name.")
	private String name;
	@NotBlank(message = "Please enter email.")
	private String email;
	@NotBlank(message = "Please enter password.")
	private String password;

}
