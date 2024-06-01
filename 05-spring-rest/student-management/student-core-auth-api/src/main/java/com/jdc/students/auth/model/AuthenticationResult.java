package com.jdc.students.auth.model;

import java.time.LocalDateTime;

public record AuthenticationResult(String code, String fullName, String authority, String accessToken,
		String refreshToken, LocalDateTime accessAt) {
	
	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private String code;
		private String fullName;
		private String authority;
		private String accessToken;
		private String refreshToken;
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

		public Builder accessToken(String accessToken) {
			this.accessToken = accessToken;
			return this;
		}

		public Builder refreshToken(String refreshToken) {
			this.refreshToken = refreshToken;
			return this;
		}

		private Builder() {
			this.accessAt = LocalDateTime.now();
		}

		public AuthenticationResult build() {
			return new AuthenticationResult(code, fullName, authority, accessToken, refreshToken, accessAt);
		}
	}
}
