package com.jdc.balance.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jdc.balance.api.input.CategoryForm;
import com.jdc.balance.api.input.CategorySearch;
import com.jdc.balance.api.output.CategoryInfo;
import com.jdc.balance.model.service.CategoryService;

@RestController
@RequestMapping("category")
public class CategoryApi {
	
	@Autowired
	private CategoryService service;

	@GetMapping
	List<CategoryInfo> search(CategorySearch search) {
		return service.search(search);
	}
	
	@PostMapping("upload")
	List<CategoryInfo> upload(@RequestParam MultipartFile file) {
		return service.upload(file);
	}
	
	@PostMapping
	CategoryInfo create(
			@Validated @RequestBody CategoryForm form, BindingResult result) {
		return service.create(form);
	}
	
	@GetMapping("{id}")
	CategoryInfo findById(@PathVariable int id) {
		return service.findById(id);
	}
	
	@PutMapping("{id}")
	CategoryInfo update(@PathVariable int id,
			@Validated @RequestBody CategoryForm form, BindingResult result) {
		return service.update(id, form);
	}
	
}
