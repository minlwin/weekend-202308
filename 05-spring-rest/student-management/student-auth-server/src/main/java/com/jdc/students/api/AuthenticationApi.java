package com.jdc.students.api;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.students.auth.model.AuthenticationForm;
import com.jdc.students.auth.model.AuthenticationResult;

@RestController
@RequestMapping("token")
public class AuthenticationApi {

	@PostMapping("generate")
	AuthenticationResult generate(
			@Validated AuthenticationForm form, BindingResult result) {
		return null;
	}
	
	@PostMapping("validate")
	AuthenticationResult validate(@RequestParam String token) {
		return null;
	}
	
	@PostMapping("refresh")
	AuthenticationResult refresh(@RequestParam String token) {
		return null;
	}	
}
