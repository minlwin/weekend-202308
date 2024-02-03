package com.jdc.weekend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.weekend.model.input.MemberRoleUpdateForm;
import com.jdc.weekend.model.input.MemberSearch;
import com.jdc.weekend.model.input.MemberStatusForm;
import com.jdc.weekend.model.output.MemberInfo;
import com.jdc.weekend.model.output.MemberInfoDetails;
import com.jdc.weekend.model.output.Pager;
import com.jdc.weekend.model.service.MemberManagementService;

@Controller
@RequestMapping("admin/members")
public class AdminMemberManagementController {
	
	@Autowired
	private MemberManagementService service;

	@GetMapping
	String search(MemberSearch search, 
			@RequestParam(required = false, defaultValue = "0") int page, 
			@RequestParam(required = false, defaultValue = "10") int size,
			ModelMap model) {
		
		Page<MemberInfo> result = service.search(search, page, size);
		model.put("list", result.getContent());
		model.put("pager", new Pager(result));
		
		return "member-list";
	}
	
	@GetMapping("{id}")
	String showDetails(@PathVariable int id, ModelMap model) {
		MemberInfoDetails member = service.findById(id);
		model.put("dto", member);
		
		var username = SecurityContextHolder.getContext().getAuthentication().getName();
		model.put("hideAction", member.email().equals(username));
				
		return "member-details";
	}
	
	@PostMapping("{id}/role")
	String changeRole(@PathVariable int id,
			@Validated MemberRoleUpdateForm form, BindingResult result) {
		
		if(!result.hasErrors()) {
			service.update(id, form);
		}
		
		return "redirect:/admin/members/%d".formatted(id);
	}
	
	@PostMapping("{id}/status")
	String updateStatus(@PathVariable int id,
			@Validated MemberStatusForm form, BindingResult result) {
		
		if(!result.hasErrors()) {
			service.update(id, form);
		}
		
		return "redirect:/admin/members/%d".formatted(id);
	}
	
}
