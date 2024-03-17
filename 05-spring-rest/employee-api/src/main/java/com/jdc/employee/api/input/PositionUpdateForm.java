package com.jdc.employee.api.input;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PositionUpdateForm(
		@NotBlank(message = "Please enter position name.")
		String name,
		@NotNull(message = "Please enter basic salary.")
		BigDecimal basicSalary,
		@NotNull(message = "Please enter OT Fees per hour.")
		BigDecimal otPerHour,
		String remark) {

}
