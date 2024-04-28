package com.jdc.balance.model;

import com.jdc.balance.model.entity.Account;

public record LoginUser(
		String loginId,
		String name,
		Role role
		) {

	public static LoginUser from(Account account) {
		return new LoginUser(
				account.getLoginId(), 
				account.getName(), 
				account.getRole());
	}
}
