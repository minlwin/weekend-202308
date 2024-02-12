package com.jdc.weekend.model.output;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.jdc.weekend.model.entity.Post;

public record PostInfoDetails(
		int id,
		String title,
		int categoryId,
		String categoryName,
		int memberId,
		String memberName,
		String description,
		LocalDateTime createAt,
		String images,
		List<PostReviewInfo> reviews
		) {
	
	public List<String> getPhotos() {
		return Arrays.asList(images.split(","));
	}

	public static PostInfoDetails from(Post entity) {
		return new PostInfoDetails(
				entity.getId(), 
				entity.getTitle(), 
				entity.getCategory().getId(), 
				entity.getCategory().getName(), 
				entity.getOwner().getId(), 
				entity.getOwner().getName(), 
				entity.getDescription(), 
				entity.getPostAt(), 
				entity.getImages(), 
				entity.getReviews().stream().map(PostReviewInfo::from).toList());
	}
}
