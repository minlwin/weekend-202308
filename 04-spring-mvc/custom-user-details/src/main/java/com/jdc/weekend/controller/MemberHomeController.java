package com.jdc.weekend.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.weekend.model.input.ChangePasswordForm;

@Controller
@RequestMapping("member/home")
public class MemberHomeController {

	@GetMapping
	String home(
			@RequestParam Optional<String> keyword,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "9") int size,
			ModelMap model) {
		return "member-home";
	}
		
	@PostMapping("change-password")
	String changePassword(@Validated ChangePasswordForm form, BindingResult result) {
		return "redirect:/member/home";
	}
}
