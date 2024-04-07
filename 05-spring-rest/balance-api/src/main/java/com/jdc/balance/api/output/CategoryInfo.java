package com.jdc.balance.api.output;

import com.jdc.balance.model.BalanceType;
import com.jdc.balance.model.entity.Category;
import com.jdc.balance.model.entity.Category_;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public record CategoryInfo(
		int id,
		BalanceType type,
		String name,
		String description
		) {

	public static CategoryInfo from(Category entity) {
		return new CategoryInfo(
				entity.getId(), 
				entity.getType(), 
				entity.getName(), 
				entity.getDescription());
	}

	public static void select(CriteriaQuery<CategoryInfo> cq, Root<Category> root) {
		cq.multiselect(
			root.get(Category_.id),
			root.get(Category_.type),
			root.get(Category_.name),
			root.get(Category_.description)
		);
	}

}
