package com.jdc.weekend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.weekend.model.input.MemberSearch;

@Controller
@RequestMapping("admin/members")
public class AdminMemberManagementController {

	@GetMapping
	String search(MemberSearch search, 
			@RequestParam(required = false, defaultValue = "0") int page, 
			@RequestParam(required = false, defaultValue = "10") int size,
			ModelMap model) {
		return "member-list";
	}
	
}
