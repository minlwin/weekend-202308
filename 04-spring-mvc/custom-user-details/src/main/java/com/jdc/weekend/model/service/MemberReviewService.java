package com.jdc.weekend.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.weekend.model.entity.Review;
import com.jdc.weekend.model.entity.ReviewPk;
import com.jdc.weekend.model.input.ReviewForm;
import com.jdc.weekend.model.repo.MemberRepo;
import com.jdc.weekend.model.repo.PostRepo;
import com.jdc.weekend.model.repo.ReviewRepo;

@Service
public class MemberReviewService {

	@Autowired
	private ReviewRepo reviewRepo;
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private MemberRepo memberRepo;
	
	@Transactional
	@PreAuthorize("hasAuthority('Member')")
	public void save(int id, ReviewForm form) {

		var username = SecurityContextHolder.getContext().getAuthentication().getName();
		var member = memberRepo.findOneByEmail(username).orElseThrow();
		var post = postRepo.findById(id).orElseThrow();
		
		var reviewId = new ReviewPk();
		reviewId.setPostId(post.getId());
		reviewId.setReviewrId(member.getId());
		
		var review = reviewRepo.findById(reviewId)
				.orElseGet(() -> {
					var entity = new Review();
					entity.setId(reviewId);
					entity.setReviewer(member);
					entity.setPost(post);
					return entity;
				});
		
		review.setRate(form.getRating());
		review.setMessage(form.getMessage());
		
		reviewRepo.save(review);
	}

}
