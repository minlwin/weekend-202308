package com.jdc.weekend.rest;

import java.time.Month;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.weekend.model.service.SummaryDataService;
import com.jdc.weekend.rest.output.NameAndCount;

@RestController
@RequestMapping("admin/api")
public class SummaryDataApi {
	
	@Autowired
	private SummaryDataService service;

	@GetMapping("top-users")
	List<NameAndCount> findTopUsers() {
		return service.findTopUsers(10);
	}
	
	@GetMapping("categories")
	List<NameAndCount> findCategorySummary() {
		return service.findCategoryData();
	}
	
	@GetMapping("post/year")
	List<NameAndCount> findYearlyData(@RequestParam int year) {
		return service.search(year);
	}
	
	@GetMapping("post/month")
	List<NameAndCount> findMonthlyData(@RequestParam int year, @RequestParam Month month) {
		return service.search(year, month);
	}
}
