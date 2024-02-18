package com.jdc.weekend.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.weekend.model.input.ReviewForm;
import com.jdc.weekend.model.output.Pager;
import com.jdc.weekend.model.service.MemberReviewService;
import com.jdc.weekend.model.service.PostReferenceService;

@Controller
@RequestMapping("public/posts")
public class PublicPostController {
	
	@Autowired
	private PostReferenceService service;
	
	@Autowired
	private MemberReviewService reviewService;

	@GetMapping
	String search(
			@RequestParam Optional<Integer> category,
			@RequestParam Optional<String> keyword,
			@RequestParam(required = false, defaultValue = "0") int page, 
			@RequestParam(required = false, defaultValue = "9") int size,
			ModelMap model) {
		
		var result = service.search(Optional.empty(), category, keyword, page, size);
		model.put("list", result.getContent());
		model.put("pager", new Pager(result));
		
		return "post-list";
	}
	
	@GetMapping("{id}")
	String findById(@PathVariable int id, ModelMap model) {
		
		model.put("dto", service.findById(id));
		return "post-details";
	}
	
	@PostMapping("{id}/review")
	@PreAuthorize("hasAuthority('Member')")
	String addReview(@PathVariable int id, 
			ModelMap model,
			@ModelAttribute("reviewForm") @Validated ReviewForm form, BindingResult result) {
		
		if(result.hasErrors()) {
			model.put("dto", service.findById(id));
			return "post-details";
		}
		
		reviewService.save(id, form);
		
		return "redirect:/public/posts/%d".formatted(id);
	}
	
	@ModelAttribute("reviewForm")
	ReviewForm reviewForm() {
		return new ReviewForm();
	}
	
}
