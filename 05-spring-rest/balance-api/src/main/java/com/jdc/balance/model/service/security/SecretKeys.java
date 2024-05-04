package com.jdc.balance.model.service.security;

import java.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.Jwts;

public class SecretKeys {

	public static SecretKey generate() {
		return Jwts.SIG.HS512.key().build();
	}
	
	public static String toString(SecretKey key) {
		var bytes = key.getEncoded();
		return Base64.getEncoder().encodeToString(bytes);
	}
	
	public static SecretKey toKey(String string) {
		var bytes = Base64.getDecoder().decode(string);
		return new SecretKeySpec(bytes, "HmacSHA512");
	}
}
