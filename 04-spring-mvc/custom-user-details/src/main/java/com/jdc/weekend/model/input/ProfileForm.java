package com.jdc.weekend.model.input;

import com.jdc.weekend.model.entity.Member;
import com.jdc.weekend.model.validation.UniqueLoginId;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProfileForm {

	private int id;
	@NotBlank(message = "Please enter user name.")
	private String name;
	
	@UniqueLoginId
	@NotBlank(message = "Please enter email address.")
	private String email;
	
	private String phone;
	private String greeting;
	
	public static ProfileForm from(Member entity) {
		var dto = new ProfileForm();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setEmail(entity.getEmail());
		dto.setPhone(entity.getPhone());
		dto.setGreeting(entity.getGreeting());
		return dto;
	}
}
