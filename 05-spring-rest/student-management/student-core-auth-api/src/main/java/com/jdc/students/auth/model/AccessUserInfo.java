package com.jdc.students.auth.model;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


public record AccessUserInfo(
		String code,
		String fullName,
		String authority,
		LocalDateTime accessAt
) {
	
	public List<GrantedAuthority> authorities() {
		return Arrays.stream(authority.split(","))
				.map(AccessUserInfo::convert)
				.toList();
	}
	
	private static GrantedAuthority convert(String str) {
		return new SimpleGrantedAuthority(str);
	}

	public static AccessUserInfo from(Authentication auth) {
		if(auth.isAuthenticated() && auth.getPrincipal() instanceof AppUserDetails user) {
			return new AccessUserInfo(
					auth.getName(), 
					user.getFullName(), 
					auth.getAuthorities().stream().map(a -> a.getAuthority()).collect(Collectors.joining(",")),
					LocalDateTime.now());
		}
		
		return null;
	}
	
	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private String code;
		private String fullName;
		private String authority;
		private LocalDateTime accessAt;

		public Builder code(String code) {
			this.code = code;
			return this;
		}

		public Builder fullName(String fullName) {
			this.fullName = fullName;
			return this;
		}

		public Builder authority(String authority) {
			this.authority = authority;
			return this;
		}

		private Builder() {
			this.accessAt = LocalDateTime.now();
		}

		public AccessUserInfo build() {
			return new AccessUserInfo(code, fullName, authority, accessAt);
		}
	}	
}
