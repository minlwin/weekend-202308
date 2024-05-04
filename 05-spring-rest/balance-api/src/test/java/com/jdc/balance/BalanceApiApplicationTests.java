package com.jdc.balance;

import org.junit.jupiter.api.Test;

import com.jdc.balance.model.service.security.SecretKeys;

class BalanceApiApplicationTests {

	@Test
	void contextLoads() {
		var key = SecretKeys.generate();
		var string = SecretKeys.toString(key);
		System.out.println(string);
	}

}
