package com.jdc.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.jdc.rest.service.SecretKeys;

class RestSecurityApplicationTests {

	
	@Test
	void testKey() {
		
		var key1 = SecretKeys.getkey();
		var str1 = SecretKeys.getKey(key1);
		
		var key2 = SecretKeys.getkey(str1);
		var str2 = SecretKeys.getKey(key2);
		
		System.out.println(str1);
		System.out.println(str2);
		
		assertEquals(str1, str2);
		
	}

}
