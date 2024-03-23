package com.jdc.rest.service;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;

@Service
public class ApiTokenProvider {
	
	private static final String ROLE = "rol";
	
	@Value("${api.token.issuer}")
	private String issuer;
	@Value("${api.token.lifetime}")
	private int lifeTime;
	@Value("${api.token.secret}")
	private String secret;
	
	private SecretKey key;
	
	@PostConstruct
	private void postConstruct() {
		key = SecretKeys.getkey(secret);
	}

	public String generate(Authentication authentication) {
		
		var roles = authentication.getAuthorities().stream()
				.map(a -> a.getAuthority()).collect(Collectors.joining(","));
		
		var issueAt = new Date();
		var calendar = Calendar.getInstance();
		calendar.setTime(issueAt);
		calendar.add(Calendar.MINUTE, lifeTime);
		
		return Jwts.builder()
			.subject(authentication.getName())
			.issuer(issuer)
			.issuedAt(issueAt)
			.expiration(calendar.getTime())
			.claim(ROLE, roles)
			.signWith(key).compact();
		
	}

	public Authentication parse(String token) {
		
		if(StringUtils.hasLength(token)) {
			
			var jwt = Jwts.parser()
				.requireIssuer(issuer)
				.verifyWith(key)
				.build()
				.parseSignedClaims(token);
			
			var username = jwt.getPayload().getSubject();
			var roles = jwt.getPayload().get(ROLE, String.class);
			var authorities = Arrays.stream(roles.split(","))
					.map(a -> new SimpleGrantedAuthority(a)).toList();
			
			return UsernamePasswordAuthenticationToken.authenticated(username, null, authorities);
		}
		
		return null;
	}

}
