package com.jdc.weekend.model.output;

import java.time.LocalDateTime;

import com.jdc.weekend.model.entity.Category;
import com.jdc.weekend.model.entity.Category_;
import com.jdc.weekend.model.entity.Post_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;

public record CategoryInfoDetails(
		int id,
		String name,
		String description,
		long posts,
		boolean deleted,
		LocalDateTime createAt,
		String createBy
		) {

	public static void select(
			CriteriaBuilder cb,
			CriteriaQuery<CategoryInfoDetails> cq, 
			Root<Category> root) {
		
		var posts = root.join(Category_.posts, JoinType.LEFT);
		
		cq.multiselect(
			root.get(Category_.id),
			root.get(Category_.name),
			root.get(Category_.description),
			cb.count(posts.get(Post_.id)),
			root.get(Category_.deleted),
			root.get(Category_.createAt),
			root.get(Category_.createBy)
		);
		
		cq.groupBy(
			root.get(Category_.id),
			root.get(Category_.name),
			root.get(Category_.description),
			root.get(Category_.deleted),
			root.get(Category_.createAt),
			root.get(Category_.createBy)
		);
		
		cq.orderBy(cb.asc(root.get(Category_.id)));
	}
}
