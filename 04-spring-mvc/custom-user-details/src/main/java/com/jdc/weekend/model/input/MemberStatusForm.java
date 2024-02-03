package com.jdc.weekend.model.input;

import jakarta.validation.constraints.NotNull;

public record MemberStatusForm(
		@NotNull(message = "Please select status.")
		Boolean deleted
		) {

}
