package com.jdc.balance.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.balance.api.input.ProfileForm;
import com.jdc.balance.api.output.ProfileInfo;
import com.jdc.balance.model.service.EmployeeService;

@RestController
@RequestMapping("profile")
public class ProfileApi {
	
	@Autowired
	private EmployeeService service;

	@GetMapping
	@PreAuthorize("isFullyAuthenticated() && #loginId == authentication.name")
	ProfileInfo getProfile(@RequestParam String loginId) {
		return service.findProfile(loginId);
	}
	
	@PutMapping
	@PreAuthorize("isFullyAuthenticated() && #form.loginId == authentication.name")
	ProfileInfo updateProfile(@Validated @RequestBody ProfileForm form, BindingResult result) {
		return service.updateProfile(form);
	}
}
