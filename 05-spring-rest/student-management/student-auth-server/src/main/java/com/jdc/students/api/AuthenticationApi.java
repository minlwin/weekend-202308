package com.jdc.students.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.students.auth.model.AccessUserInfo;
import com.jdc.students.auth.model.AuthenticationForm;
import com.jdc.students.auth.model.AuthenticationResult;
import com.jdc.students.service.AppAuthenticationService;

@RestController
@RequestMapping("token")
public class AuthenticationApi {
	
	@Autowired
	private AppAuthenticationService service;

	@PostMapping("generate")
	AuthenticationResult generate(
			@Validated AuthenticationForm form, BindingResult result) {
		return service.generate(form);
	}
	
	@PostMapping("validate")
	AccessUserInfo validate(@RequestParam String token) {
		return service.validate(token);
	}
	
	@PostMapping("refresh")
	AuthenticationResult refresh(@RequestParam String token) {
		return service.refresh(token);
	}	
}
