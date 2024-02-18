package com.jdc.weekend.model.output;

import java.time.LocalDateTime;

import com.jdc.weekend.model.entity.Review;

public record PostReviewInfo(
		int reviewerId,
		String reviewerName,
		LocalDateTime reviewAt,
		int rating,
		String message
		) {
	
	public String[] getStars() {
		return Star.getStars(rating);
	}

	public static PostReviewInfo from(Review entity) {
		return new PostReviewInfo(
				entity.getReviewer().getId(), 
				entity.getReviewer().getName(), 
				entity.getCreateAt(), 
				entity.getRate(), 
				entity.getMessage());
	}
}
