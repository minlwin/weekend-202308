package com.jdc.weekend.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.weekend.model.output.Pager;
import com.jdc.weekend.model.service.PublicPostService;

@Controller
@RequestMapping("public/posts")
public class PublicPostController {
	
	@Autowired
	private PublicPostService service;

	@GetMapping
	String search(
			@RequestParam Optional<Integer> category,
			@RequestParam Optional<String> keyword,
			@RequestParam(required = false, defaultValue = "0") int page, 
			@RequestParam(required = false, defaultValue = "9") int size,
			ModelMap model) {
		
		var result = service.search(category, keyword, page, size);
		model.put("list", result.getContent());
		model.put("pager", new Pager(result));
		
		return "post-list";
	}
	
	@GetMapping("{id}")
	String findById(@PathVariable int id, ModelMap model) {
		
		model.put("dto", service.findById(id));
		return "post-details";
	}
}
