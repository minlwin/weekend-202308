package com.jdc.weekend.model.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class ReviewPk implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "post_id", nullable = false)
	private int postId;
	@Column(name = "reviewer_id", nullable = false)
	private int reviewrId;
}
