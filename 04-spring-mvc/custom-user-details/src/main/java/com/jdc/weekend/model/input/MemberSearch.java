package com.jdc.weekend.model.input;

import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.jdc.weekend.model.entity.Member;
import com.jdc.weekend.model.entity.Member_;
import com.jdc.weekend.model.entity.Member.Role;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record MemberSearch(
	Role role,
	Boolean status,
	String name,
	String phone,
	String email) {

	public Predicate[] where(CriteriaBuilder cb, Root<Member> root) {
		
		var list = new ArrayList<Predicate>();
		
		if(StringUtils.hasLength(email)) {
			list.add(cb.like(root.get(Member_.email), email.concat("%")));
		}
		
		if(null != role) {
			list.add(cb.equal(root.get(Member_.role), role));
		}
		
		if(null != status) {
			list.add(cb.equal(root.get(Member_.deleted), status));
		}
		
		if(StringUtils.hasLength(name)) {
			list.add(cb.like(cb.lower(root.get(Member_.name)), name.toLowerCase().concat("%")));
		}

		if(StringUtils.hasLength(phone)) {
			list.add(cb.like(root.get(Member_.phone), phone.concat("%")));
		}

		return list.toArray(size -> new Predicate[size]);
	}
}
