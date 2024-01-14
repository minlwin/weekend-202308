package com.jdc.weekend.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.weekend.model.output.CategoryInfo;

@Controller
@RequestMapping("admin/category")
public class AdminCategoryController {

	@GetMapping
	String search(@RequestParam Optional<String> keyword) {
		return "category-list";
	}
	
	@PostMapping
	String save(@Validated CategoryInfo form, BindingResult result) {
		return "redirect:/admin/category";
	}
}
