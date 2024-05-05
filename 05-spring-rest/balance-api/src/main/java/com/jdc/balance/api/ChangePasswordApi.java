package com.jdc.balance.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.balance.api.input.ChangePasswordForm;
import com.jdc.balance.model.service.EmployeeService;

@RestController
@RequestMapping("change-password")
public class ChangePasswordApi {
	
	@Autowired
	private EmployeeService service;

	@PostMapping
	@PreAuthorize("isFullyAuthenticated() && authentication.name == #form.loginId")
	String change(@Validated @RequestBody ChangePasswordForm form, BindingResult result) {
		return service.changePassword(form);
	}
}
