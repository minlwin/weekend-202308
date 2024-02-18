package com.jdc.weekend.model.input;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReviewForm {
	
	@NotEmpty(message = "Please enter review message.")
	private String message;
	
	@Max(value = 10, message = "Maximum rating value is 10.")
	@NotNull(message = "Please select rating.")
	private Integer rating;
}
