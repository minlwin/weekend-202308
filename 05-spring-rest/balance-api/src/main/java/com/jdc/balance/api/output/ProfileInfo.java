package com.jdc.balance.api.output;

import java.time.LocalDateTime;

import com.jdc.balance.model.Role;
import com.jdc.balance.model.Status;
import com.jdc.balance.model.entity.Employee;

public record ProfileInfo(
		int id,
		String name,
		Role role,
		String email,
		String phone,
		Status status,
		LocalDateTime createdAt,
		String createdBy,
		LocalDateTime lastUpdatedAt,
		String lastUpdatedBy
		) {

	public static ProfileInfo from(Employee entity) {
		return new ProfileInfo(
				entity.getId(), 
				entity.getAccount().getName(), 
				entity.getAccount().getRole(), 
				entity.getEmail(), 
				entity.getPhone(),
				entity.getStatus(), 
				entity.getCreateAt(), 
				entity.getCreateBy(), 
				entity.getModifyAt(),
				entity.getModifyBy());
	}
}
