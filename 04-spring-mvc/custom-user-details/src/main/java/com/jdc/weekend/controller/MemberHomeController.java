package com.jdc.weekend.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.weekend.model.input.ChangePasswordForm;
import com.jdc.weekend.model.output.Pager;
import com.jdc.weekend.model.service.PostReferenceService;

@Controller
@RequestMapping("member/home")
public class MemberHomeController {
	
	@Autowired
	private PostReferenceService service;

	@GetMapping
	String home(
			@RequestParam Optional<String> keyword,
			@RequestParam Optional<Integer> category,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "9") int size,
			ModelMap model) {
		
		var username = SecurityContextHolder.getContext().getAuthentication().getName();
		var result = service.search(Optional.of(username), category, keyword, page, size);

		model.put("list", result.getContent());
		model.put("pager", new Pager(result));
		
		return "member-home";
	}
		
	@PostMapping("change-password")
	String changePassword(@Validated ChangePasswordForm form, BindingResult result) {
		return "redirect:/member/home";
	}
}
