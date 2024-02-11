package com.jdc.weekend.model.output;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.jdc.weekend.model.entity.Category_;
import com.jdc.weekend.model.entity.Member_;
import com.jdc.weekend.model.entity.Post;
import com.jdc.weekend.model.entity.Post_;
import com.jdc.weekend.model.entity.Review_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;

public record PostInfo(
		int id,
		String title,
		int categoryId,
		String categoryName,
		int memberId,
		String memberName,
		String description,
		LocalDateTime createAt,
		String images,
		Integer rating,
		Long ratingCount
		){
	
	public List<String> getPhotos() {
		return Arrays.asList(images.split(","));
	}

	public static void select(CriteriaBuilder cb, CriteriaQuery<PostInfo> cq, Root<Post> root) {
		
		var reviews = root.join(Post_.reviews, JoinType.LEFT);
		
		cq.multiselect(
			root.get(Post_.id),	
			root.get(Post_.title),	
			root.get(Post_.category).get(Category_.id),	
			root.get(Post_.category).get(Category_.name),	
			root.get(Post_.owner).get(Member_.id),	
			root.get(Post_.owner).get(Member_.name),	
			root.get(Post_.description),	
			root.get(Post_.createAt),	
			root.get(Post_.images),
			cb.sum(reviews.get(Review_.rate)),
			cb.count(reviews.get(Review_.id))
		);
		
		cq.groupBy(
				root.get(Post_.id),	
				root.get(Post_.title),	
				root.get(Post_.category).get(Category_.id),	
				root.get(Post_.category).get(Category_.name),	
				root.get(Post_.owner).get(Member_.id),	
				root.get(Post_.owner).get(Member_.name),	
				root.get(Post_.description),	
				root.get(Post_.createAt)
		);
	}
}
