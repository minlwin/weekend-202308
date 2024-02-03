package com.jdc.weekend.model.output;

import java.time.LocalDateTime;

import com.jdc.weekend.model.entity.Member;
import com.jdc.weekend.model.entity.Member.Role;
import com.jdc.weekend.model.entity.Member_;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public record MemberInfo(
		int id,
		String name,
		Role role,
		String phone,
		String email,
		LocalDateTime createAt,
		boolean deleted) {

	public static void select(CriteriaQuery<MemberInfo> cq, Root<Member> root) {
		cq.multiselect(
			root.get(Member_.id),
			root.get(Member_.name),
			root.get(Member_.role),
			root.get(Member_.phone),
			root.get(Member_.email),
			root.get(Member_.createAt),
			root.get(Member_.deleted)
		);
	}

}
