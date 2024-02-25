package com.jdc.weekend.controller;

import java.time.Month;
import java.time.YearMonth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.weekend.model.service.SummaryDataService;

@Controller
@RequestMapping("admin/home")
public class AdminHomeController {
	
	@Autowired
	private SummaryDataService service;

	@GetMapping
	String index(ModelMap model) {
		// Year List
		model.put("years", service.getBusinessYears());
		
		// Current Year
		model.put("currentYear", YearMonth.now().getYear());
		
		// Month List
		model.put("months", Month.values());
		
		// Current Month
		model.put("currentMonth", YearMonth.now().getMonth());
		
		return "admin-home";
	}
}
