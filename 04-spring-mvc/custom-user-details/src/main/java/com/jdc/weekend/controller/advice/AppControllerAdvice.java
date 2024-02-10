package com.jdc.weekend.controller.advice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.jdc.weekend.model.output.CategoryInfo;
import com.jdc.weekend.model.output.MemberProfile;
import com.jdc.weekend.model.service.CategoryReferenceService;
import com.jdc.weekend.model.service.MemberProfileService;

@ControllerAdvice
public class AppControllerAdvice {
	
	@Autowired
	private CategoryReferenceService categoryReferenceService;
	@Autowired
	private MemberProfileService profileService;
	
	@ModelAttribute(name = "categories")
	List<CategoryInfo> categories() {
		return categoryReferenceService.findActiveCategories();
	}
	
	@ModelAttribute(name = "profile")
	MemberProfile profile() {
		return profileService.getProfile().orElse(null);
	}
}
