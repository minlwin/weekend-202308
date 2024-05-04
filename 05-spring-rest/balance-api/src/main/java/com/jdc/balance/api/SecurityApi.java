package com.jdc.balance.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.balance.api.input.TokenRequest;
import com.jdc.balance.api.output.TokenResponse;
import com.jdc.balance.model.service.security.SecurityService;

@RestController
@RequestMapping("token")
public class SecurityApi {
	
	@Autowired
	private SecurityService service;

	@PostMapping("generate")
	TokenResponse generate(@Validated TokenRequest request, BindingResult result) {
		return service.generate(request);
	}
	
	@PostMapping("refresh")
	TokenResponse refresh(@RequestParam String refreshToken) {
		return service.refresh(refreshToken);
	}
}
