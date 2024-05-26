package com.jdc.students.domain.account.entity;

import com.jdc.students.domain.account.entity.Account.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class AccountCodeSeq {

	@Id
	private Role role;
	private int seqNumber;
	
	public String next() {
		return "%s-%06d".formatted(role.getShortCode(), ++ seqNumber);
	}
}
