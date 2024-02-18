package com.jdc.weekend.model.output;

import java.time.LocalDateTime;
import java.util.List;

import com.jdc.weekend.model.entity.Post;

public record PostInfoDetails(
		int id,
		String title,
		int categoryId,
		String categoryName,
		int memberId,
		String memberName,
		String memberEmail,
		String description,
		LocalDateTime createAt,
		String images,
		List<PostReviewInfo> reviews
		) {
	
	public List<String> getPhotos() {
		return List.of(images.split(","));
	}
	
	public String[] getStars() {
		
		if(reviews.isEmpty()) {
			return Star.getStars(0);
		}
		
		var totalRating = reviews.stream().mapToInt(a -> a.rating())
				.sum();
		var average = totalRating / reviews.size();
		
		return Star.getStars(average);
	}

	public static PostInfoDetails from(Post entity) {
		return new PostInfoDetails(
				entity.getId(), 
				entity.getTitle(), 
				entity.getCategory().getId(), 
				entity.getCategory().getName(), 
				entity.getOwner().getId(), 
				entity.getOwner().getName(), 
				entity.getOwner().getEmail(),
				entity.getDescription(), 
				entity.getPostAt(), 
				entity.getImages(), 
				entity.getReviews().stream().map(PostReviewInfo::from).toList());
	}
}
