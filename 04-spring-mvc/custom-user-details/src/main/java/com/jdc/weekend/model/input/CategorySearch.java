package com.jdc.weekend.model.input;

import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.jdc.weekend.model.entity.Category;
import com.jdc.weekend.model.entity.Category_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record CategorySearch(
		Boolean deleted,
		String keyword
		) {

	public Predicate[] where(CriteriaBuilder cb, Root<Category> root) {
		var list = new ArrayList<Predicate>();
		
		if(null != deleted) {
			list.add(cb.equal(root.get(Category_.deleted), deleted));
		}
		
		if(StringUtils.hasLength(keyword)) {
			list.add(cb.or(
				cb.like(cb.lower(root.get(Category_.name)), keyword.toLowerCase().concat("%")),
				cb.like(cb.lower(root.get(Category_.description)), "%%%s%%".formatted(keyword.toLowerCase()))
			));
		}
		
		return list.toArray(size -> new Predicate[size]);
	}
}
