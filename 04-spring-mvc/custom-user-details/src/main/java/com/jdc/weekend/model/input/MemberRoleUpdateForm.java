package com.jdc.weekend.model.input;

import com.jdc.weekend.model.entity.Member.Role;

import jakarta.validation.constraints.NotNull;

public record MemberRoleUpdateForm(
		@NotNull(message = "Please select role.")
		Role role
		) {

}
