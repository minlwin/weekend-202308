package com.jdc.weekend.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@SequenceGenerator(name = "member_seq", allocationSize = 1)
public class Member extends AbstractEntity{
	
	@Id
	@GeneratedValue(generator = "member_seq")
	private int id;
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false)
	private String name;
	
	private String phone;
	private String profileImage;
	private String greeting;
	
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private Role role;
	
	public enum Role {
		Admin, Member
	}
}
