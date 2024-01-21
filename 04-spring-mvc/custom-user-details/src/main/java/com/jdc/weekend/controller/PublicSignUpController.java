package com.jdc.weekend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.weekend.model.input.SignUpForm;
import com.jdc.weekend.model.service.SignUpService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("public/signup")
public class PublicSignUpController {
	
	@Autowired
	private SignUpService service;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private SecurityContextRepository securityContextRepository;

	@GetMapping
	String index() {
		return "sign-up";
	}
	
	@PostMapping
	String signUp(
			HttpServletRequest request, HttpServletResponse response,
			@Validated @ModelAttribute("signUpForm") SignUpForm signUpForm, BindingResult result) {
		
		if(result.hasErrors()) {
			return "sign-up";
		}
		
		service.signUp(signUpForm);
		
		// Authenticate User
		Authentication authentication = UsernamePasswordAuthenticationToken.unauthenticated(signUpForm.getEmail(), signUpForm.getPassword());
		authentication = authenticationManager.authenticate(authentication);
		
		// Add Authentication Result to Security Context Holder
		var context = SecurityContextHolder.getContext();
		context.setAuthentication(authentication);
		
		securityContextRepository.saveContext(context, request, response);
		
		return "redirect:/member/home";
	}
	
	@ModelAttribute("signUpForm")
	SignUpForm signUpForm() {
		return new SignUpForm();
	}
}
