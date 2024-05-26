package com.jdc.students.domain.account.entity;

import com.jdc.students.domain.AbstractEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Account extends AbstractEntity{

	@Id
	private String code;
	
	@Column(nullable = false)
	private String fullName;
	
	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private Role role;
	
	@Column(columnDefinition = "boolean default false")
	private boolean activated;	
	
	@OneToOne(mappedBy = "account")
	private LoginUser loginUser;
	
	public enum Role {
		Admin("ADM"), 
		Student("STD"), 
		Office("OFC"), 
		Teacher("TCR");
		
		private String shortCode;
		
		private Role(String shortCode) {
			this.shortCode = shortCode;
		}

		public String getShortCode() {
			return shortCode;
		}
	}
}
