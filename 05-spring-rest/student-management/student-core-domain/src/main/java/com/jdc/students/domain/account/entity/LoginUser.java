package com.jdc.students.domain.account.entity;

import com.jdc.students.domain.AbstractEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class LoginUser extends AbstractEntity{

	@Id
	private String code;
	
	@OneToOne
	@MapsId("code")
	private Account account;
	
	@Column(nullable = false)
	private String phone;

	@Column(nullable = false)
	private String email;

	public void setAccount(Account account) {
		this.account = account;
		this.account.setLoginUser(this);
	}
}
