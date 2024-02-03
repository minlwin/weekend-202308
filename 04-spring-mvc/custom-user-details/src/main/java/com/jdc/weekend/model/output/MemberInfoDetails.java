package com.jdc.weekend.model.output;

import java.time.LocalDateTime;

import com.jdc.weekend.model.entity.Member;
import com.jdc.weekend.model.entity.Member.Role;

public record MemberInfoDetails(
		int id,
		String name,
		String profile,
		String greeting,
		Role role,
		String phone,
		String email,
		LocalDateTime createAt,
		boolean deleted,
		LocalDateTime updateAt,
		String updateBy) {

	public static MemberInfoDetails from(Member entity) {
		return new MemberInfoDetails(
				entity.getId(),
				entity.getName(),
				entity.getProfileImage(),
				entity.getGreeting(),
				entity.getRole(),
				entity.getPhone(),
				entity.getEmail(),
				entity.getCreateAt(),
				entity.isDeleted(),
				entity.getUpdateAt(),
				entity.getUpdateBy()
				);
	}
}
