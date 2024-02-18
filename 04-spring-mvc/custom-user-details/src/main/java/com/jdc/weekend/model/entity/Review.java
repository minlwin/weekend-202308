package com.jdc.weekend.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Review extends AbstractEntity{

	@EmbeddedId
	private ReviewPk id = new ReviewPk();
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Post post;
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "reviewer_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Member reviewer;
	
	@Column(nullable = false)
	private int rate;
	@Column(nullable = false)
	private String message;
	
	public void setPost(Post post) {
		this.post = post;
		this.id.setPostId(post.getId());
	}
	
	public void setMember(Member reviewer) {
		this.reviewer = reviewer;
		this.id.setReviewrId(reviewer.getId());
	}
}
