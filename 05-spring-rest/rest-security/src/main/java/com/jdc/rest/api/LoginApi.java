package com.jdc.rest.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.rest.api.model.LoginForm;
import com.jdc.rest.api.model.LoginResult;
import com.jdc.rest.exceptions.ApiValidationException;
import com.jdc.rest.service.LoginService;

@RestController
@RequestMapping("public")
public class LoginApi {
	
	@Autowired
	private LoginService service;

	@PostMapping("login")
	LoginResult login(@Validated @RequestBody LoginForm form, BindingResult result) {
		
		if(result.hasErrors()) {
			throw new ApiValidationException(result.getFieldErrors().stream()
					.map(a -> a.getDefaultMessage()).toList());
		}
		
		return service.login(form);
	}
}
