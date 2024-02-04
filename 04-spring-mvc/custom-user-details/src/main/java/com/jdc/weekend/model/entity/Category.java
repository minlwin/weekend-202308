package com.jdc.weekend.model.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@SequenceGenerator(name = "category_seq", allocationSize = 1)
public class Category extends AbstractEntity{

	@Id
	@GeneratedValue(generator = "category_seq")
	private int id;
	
	@Column(nullable = false, unique = true)
	private String name;
	private String description;
	
	@OneToMany(mappedBy = "category")
	private List<Post> posts;
}
