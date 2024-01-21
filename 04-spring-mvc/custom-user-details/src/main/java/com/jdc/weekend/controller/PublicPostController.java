package com.jdc.weekend.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("public/posts")
public class PublicPostController {

	@GetMapping
	String search(
			@RequestParam Optional<String> keyword,
			@RequestParam(required = false, defaultValue = "0") int page, 
			@RequestParam(required = false, defaultValue = "9") int size,
			ModelMap model) {
		
		model.put("data", """
				<h2>This is Data</h2>
				""");
		
		return "post-list";
	}
	
	@GetMapping("{id}")
	String findById(@PathVariable int id, ModelMap model) {
		return "post-details";
	}
}
