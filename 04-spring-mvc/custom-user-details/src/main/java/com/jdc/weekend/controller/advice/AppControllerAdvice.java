package com.jdc.weekend.controller.advice;

import java.util.List;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.jdc.weekend.model.output.CategoryInfo;

@ControllerAdvice
public class AppControllerAdvice {

	@ModelAttribute(name = "categories")
	List<CategoryInfo> categories() {
		return null;
	}
}
