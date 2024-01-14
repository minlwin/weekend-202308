package com.jdc.weekend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/home")
public class AdminHomeController {

	@GetMapping
	String index(ModelMap model) {
		return "admin-home";
	}
}
