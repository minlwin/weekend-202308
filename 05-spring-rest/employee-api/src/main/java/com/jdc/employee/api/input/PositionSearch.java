package com.jdc.employee.api.input;

import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.jdc.employee.model.entity.Position;
import com.jdc.employee.model.entity.Position_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record PositionSearch(
		String code,
		String name
		) {

	public Predicate[] where(CriteriaBuilder cb, Root<Position> root) {
		var list = new ArrayList<Predicate>();
		
		if(StringUtils.hasLength(code)) {
			list.add(cb.like(cb.lower(root.get(Position_.code)), code.toLowerCase().concat("%")));
		}
		
		if(StringUtils.hasLength(name)) {
			list.add(cb.like(cb.lower(root.get(Position_.name)), name.toLowerCase().concat("%")));
		}

		return list.toArray(size -> new Predicate[size]);
	}
}
