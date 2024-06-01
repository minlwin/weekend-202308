package com.jdc.students.service;

import java.util.Calendar;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jdc.students.auth.model.AccessUserInfo;
import com.jdc.students.exceptions.AppTokenInvalidationException;

import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;

@Service
public class JwtTokenProvider {
	
	private static final String ROLE = "rol";
	private static final String TYPE = "typ";
	private static final String FULL_NAME = "fun";
	
	public enum TokenType {
		Access, Refresh
	}
	
	@Value("${app.token.prefix}")
	private String prefix;
	
	@Value("${app.token.issuer}")
	private String issuer;
	@Value("${app.token.expiration.access}")
	private int accessExpiration;
	@Value("${app.token.expiration.refresh}")
	private int refreshExpiration;
	
	@Value("${app.token.secret}")
	private String secret;

	private SecretKey secretKey;
	
	@PostConstruct
	private void postConstruct() {
		secretKey = SecretKeys.toKey(secret);
	}

	public String generateAccessToken(AccessUserInfo userInfo) {
		return generate(userInfo, accessExpiration, TokenType.Access);
	}

	public String generateRefreshToken(AccessUserInfo userInfo) {
		return generate(userInfo, refreshExpiration, TokenType.Refresh);
	}

	public AccessUserInfo validate(String token, TokenType type) {
		var payload = Jwts.parser()
				.requireIssuer(issuer)
				.verifyWith(secretKey)
				.build().parseSignedClaims(token.substring(prefix.length())).getPayload();
		
		var tokenType = payload.get(TYPE, TokenType.class);
		
		if(type != tokenType) {
			throw new AppTokenInvalidationException("Invalid token type.");
		}
		
		return AccessUserInfo.builder()
				.code(payload.getSubject())
				.fullName(payload.get(FULL_NAME, String.class))
				.authority(payload.get(ROLE, String.class))
				.build();
	}
	
	private String generate(AccessUserInfo user, int expiration, TokenType type) {
		
		var issueAt = new Date();
		var expireAt = Calendar.getInstance();
		expireAt.setTime(issueAt);
		expireAt.add(Calendar.MINUTE, expiration);
		
		var token = Jwts.builder()
			.signWith(secretKey)
			.issuer(issuer)
			.issuedAt(issueAt)
			.expiration(expireAt.getTime())
			.subject(user.code())
			.claim(FULL_NAME, user.fullName())
			.claim(ROLE, user.authority())
			.claim(TYPE, type)
			.compact();
		
		return "%s%s".formatted(prefix, token);
	}	

}
