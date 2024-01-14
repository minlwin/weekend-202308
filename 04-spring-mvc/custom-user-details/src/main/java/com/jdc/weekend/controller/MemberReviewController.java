package com.jdc.weekend.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.weekend.model.input.ReviewForm;
import com.jdc.weekend.model.input.ReviewSearch;

@Controller
@RequestMapping("member/review")
public class MemberReviewController {

	@GetMapping
	String search(ReviewSearch search, 
			@RequestParam(required = false, defaultValue = "0") int page, 
			@RequestParam(required = false, defaultValue = "9") int size,
			ModelMap model) {
		return "review-list";
	}
	
	@GetMapping("edit")
	String edit(@RequestParam Optional<String> id) {
		return "review-edit";
	}
	
	@PostMapping("edit")
	String save(@Validated @ModelAttribute(name = "form") ReviewForm form, BindingResult result) {
		
		if(result.hasErrors()) {
			return "review-edit";
		}
		
		return "redirect:/public/posts/%s".formatted(0);
	}
	
	@ModelAttribute(name = "form")
	ReviewForm form(@RequestParam Optional<String> id) {
		return null;
	}
}
