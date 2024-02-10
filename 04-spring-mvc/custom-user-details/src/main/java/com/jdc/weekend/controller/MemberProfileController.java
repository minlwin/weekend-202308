package com.jdc.weekend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.weekend.model.input.ProfileForm;
import com.jdc.weekend.model.service.MemberProfileService;

@Controller
@RequestMapping("member/profile")
public class MemberProfileController {
	
	@Autowired
	private MemberProfileService service;

	@GetMapping
	String editProfile() {
		return "member-profile-edit";
	}
	
	@PostMapping
	String saveProfile(
			@Validated @ModelAttribute(name = "form") ProfileForm form, BindingResult result) {
		
		if(result.hasErrors()) {
			return "member-profile-edit";
		}
		
		service.save(form);
		
		return "redirect:/member/home";
	}
	
	@PostAuthorize("returnObject != null && returnObject.email == authentication.principal.username")
	@ModelAttribute(name = "form")
	ProfileForm form() {
		return service.getProfileForEdit().orElseThrow();
	}

}
