package com.jdc.weekend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.weekend.model.CategorySearchHolder;
import com.jdc.weekend.model.input.CategoryForm;
import com.jdc.weekend.model.input.CategorySearch;
import com.jdc.weekend.model.service.CategoryManagementService;

@Controller
@RequestMapping("admin/category")
public class AdminCategoryController {
	
	@Autowired
	private CategoryManagementService service;
	
	@Autowired
	private CategorySearchHolder searchHolder;

	@GetMapping
	String search(CategorySearch search, ModelMap model) {
		searchHolder.setSearch(search);
		model.put("list", service.search(search));
		model.put("showDialog", false);
		return "category-list";
	}
	
	@PostMapping
	String save(@Validated @ModelAttribute("form") CategoryForm form, BindingResult result, ModelMap model) {
		
		if(result.hasErrors()) {
			model.put("error", true);
			model.put("list", service.search(searchHolder.getSearch()));
			return "category-list";
		}
		
		service.save(form);
		
		return "redirect:/admin/category";
	}
	
	@ModelAttribute("form")
	CategoryForm form(@RequestParam(required = false, defaultValue = "0") int id) {
		if(id > 0) {
			return service.findEditFromById(id);
		}
		
		return new CategoryForm();
	}
}
