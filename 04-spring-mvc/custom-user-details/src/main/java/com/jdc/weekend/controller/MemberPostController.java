package com.jdc.weekend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.weekend.model.input.PostForm;

@Controller
@RequestMapping("member/post")
public class MemberPostController {

	@GetMapping
	String edit(@RequestParam(required = false, defaultValue = "0") int id) {
		return "post-edit";
	}
	
	@PostMapping
	String save(
			@Validated @ModelAttribute(name = "form") PostForm form, BindingResult result) {
		return "redirect:/public/post/%d".formatted(0);
	}
	
	@ModelAttribute
	PostForm form(@RequestParam(required = false, defaultValue = "0") int id) {
		return null;
	}
}
