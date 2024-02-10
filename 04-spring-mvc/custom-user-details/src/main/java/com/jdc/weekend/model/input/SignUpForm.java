package com.jdc.weekend.model.input;

import com.jdc.weekend.model.validation.UniqueLoginId;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignUpForm {

	@NotBlank(message = "Please enter name.")
	private String name;
	
	@UniqueLoginId
	@NotBlank(message = "Please enter email.")
	private String email;
	
	@NotBlank(message = "Please enter password.")
	private String password;

}
