package com.jdc.weekend.model.output;

import com.jdc.weekend.model.entity.Member;

public record MemberProfile(int id, String name, String email, String greeting, String profileImage) {

	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {
		private int id;
		private String name;
		private String email;
		private String greeting;
		private String profileImag;
		
		private Builder() {
		}

		public Builder id(int id) {
			this.id = id;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder email(String email) {
			this.email = email;
			return this;
		}

		public Builder greeting(String greeting) {
			this.greeting = greeting;
			return this;
		}

		public Builder profileImage(String profileImag) {
			this.profileImag = profileImag;
			return this;
		}

		public MemberProfile build() {
			return new MemberProfile(id, name, email, greeting, profileImag);
		}
	}

	public static MemberProfile from(Member entity) {
		return builder().id(entity.getId())
				.name(entity.getName())
				.email(entity.getEmail())
				.greeting(entity.getGreeting())
				.profileImage(entity.getProfileImage())
				.build();
	}
}
