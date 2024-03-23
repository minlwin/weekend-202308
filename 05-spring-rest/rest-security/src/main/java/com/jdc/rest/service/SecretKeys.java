package com.jdc.rest.service;

import java.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.Jwts;

public class SecretKeys {

	public static SecretKey getkey() {
		return Jwts.SIG.HS512.key().build();
	}
	
	public static String getKey(SecretKey key) {
		var bytes = key.getEncoded();
		return Base64.getEncoder().encodeToString(bytes);
	}
	
	public static SecretKey getkey(String key) {
		var bytes = Base64.getDecoder().decode(key);
		return new SecretKeySpec(bytes, "HmacSHA512");
	}
	
}
