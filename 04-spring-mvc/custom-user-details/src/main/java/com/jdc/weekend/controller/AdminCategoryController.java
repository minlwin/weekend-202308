package com.jdc.weekend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.weekend.model.input.CategoryForm;
import com.jdc.weekend.model.input.CategorySearch;
import com.jdc.weekend.model.service.CategoryManagementService;

@Controller
@RequestMapping("admin/category")
public class AdminCategoryController {
	
	@Autowired
	private CategoryManagementService service;

	@GetMapping
	String search(CategorySearch search, ModelMap model) {
		model.put("list", service.search(search));
		return "category-list";
	}
	
	@PostMapping
	String save(@Validated CategoryForm form, BindingResult result, ModelMap model) {
		
		if(result.hasErrors()) {
			model.put("error", true);
			return "category-list";
		}
		
		service.save(form);
		
		return "redirect:/admin/category";
	}
	
	@PostMapping("{id}")
	String update(@PathVariable int id, 
			@Validated CategoryForm form, BindingResult result, ModelMap model) {
		
		if(result.hasErrors()) {
			model.put("error", true);
			return "category-list";
		}

		service.save(id, form);

		return "redirect:/admin/category";
	}	
}
