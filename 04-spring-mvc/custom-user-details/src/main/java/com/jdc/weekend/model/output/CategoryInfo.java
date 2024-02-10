package com.jdc.weekend.model.output;

import com.jdc.weekend.model.entity.Category;
import com.jdc.weekend.model.entity.Category_;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public record CategoryInfo(
		int id,
		String name) {
	
	public static void select(CriteriaQuery<CategoryInfo> cq, Root<Category> root) {
		cq.multiselect(
				root.get(Category_.id),
				root.get(Category_.name)
		);
	}
}
