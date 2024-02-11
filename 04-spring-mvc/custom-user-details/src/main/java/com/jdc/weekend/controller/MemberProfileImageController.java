package com.jdc.weekend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jdc.weekend.model.service.MemberProfileService;

@Controller
@RequestMapping("member/profile/image")
public class MemberProfileImageController {
	
	@Autowired
	private MemberProfileService service;

	@PostMapping
	public String upload(@RequestParam MultipartFile file) {
		service.uploadPhoto(file);
		return "redirect:/member/home";
	}
}
