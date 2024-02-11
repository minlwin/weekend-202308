package com.jdc.weekend.model.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@SequenceGenerator(name = "post_seq", allocationSize = 1)
public class Post extends AbstractEntity{

	@Id
	@GeneratedValue(generator = "post_seq")
	private int id;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Member owner;
	
	@ManyToOne(optional = false)
	private Category category;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String images;
	
	@Column(nullable = false)
	private String description;
	
	@Column(nullable = false)
	private LocalDateTime postAt;
	
	@OneToMany(mappedBy = "post")
	private List<Review> reviews;

}
