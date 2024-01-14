package com.jdc.weekend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.weekend.model.input.ProfileForm;

@Controller
@RequestMapping("member/profile")
public class MemberProfileController {

	@GetMapping
	String profile() {
		return "member-profile";
	}
	
	@GetMapping("edit")
	String editProfile() {
		return "member-profile-edit";
	}
	
	@PostMapping("edit")
	String saveProfile(
			@Validated @ModelAttribute(name = "form") ProfileForm form, BindingResult result) {
		return "redirect:/member/profile";
	}
	
	@ModelAttribute(name = "form")
	ProfileForm form() {
		return null;
	}

}
