package com.jdc.weekend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.weekend.model.AppBusinessException;
import com.jdc.weekend.model.input.PostForm;
import com.jdc.weekend.model.service.PostManagementService;

@Controller
@RequestMapping("member/post")
public class MemberPostController {
	
	@Autowired
	private PostManagementService service;

	@GetMapping
	String edit(@RequestParam(required = false, defaultValue = "0") int id) {
		return "post-edit";
	}
	
	@PostMapping
	String save(
			@Validated @ModelAttribute(name = "form") PostForm form, BindingResult result) {
		
		if(result.hasErrors()) {
			return "post-edit";
		}
		
		var id = form.getId();
		
		try {
			id = service.save(form);
		} catch (AppBusinessException e) {
			result.rejectValue("files", e.getMessage(), e.getMessage());
			return "post-edit";
		}
		
		return "redirect:/public/posts/%d".formatted(id);
	}
	
	@ModelAttribute(name = "form")
	PostForm form(@RequestParam(required = false, defaultValue = "0") int id) {
		
		if(id == 0) {
			return new PostForm();
		}
		
		return service.findForEdit(id);
	}
}
