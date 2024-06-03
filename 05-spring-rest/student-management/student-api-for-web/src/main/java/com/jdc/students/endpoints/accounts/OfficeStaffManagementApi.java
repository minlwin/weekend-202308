package com.jdc.students.endpoints.accounts;

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

import com.jdc.students.endpoints.accounts.input.OfficeStaffForm;
import com.jdc.students.endpoints.accounts.input.OfficeStaffSearch;
import com.jdc.students.endpoints.accounts.output.OfficeStaffInfo;
import com.jdc.students.endpoints.accounts.service.OfficeStaffService;
import com.jdc.students.utils.dto.PageInfo;

@RestController
@RequestMapping("admin/office")
public class OfficeStaffManagementApi {
	
	@Autowired
	private OfficeStaffService service;

	@GetMapping
	PageInfo<OfficeStaffInfo> search(OfficeStaffSearch form, 
			@RequestParam(required = false, defaultValue = "0") int page, 
			@RequestParam(required = false, defaultValue = "10") int size) {
		return service.search(form, page, size);
	}
	
	@PostMapping
	OfficeStaffInfo create(@RequestBody @Validated OfficeStaffForm form, BindingResult result) {
		return service.create(form);
	}
	
	@GetMapping("{code}")
	OfficeStaffInfo findById(@PathVariable String code) {
		return service.findById(code);
	}

	@PutMapping("{code}")
	OfficeStaffInfo update(@PathVariable String code, 
			@RequestBody @Validated OfficeStaffForm form, BindingResult result) {
		return service.update(code, form);
	}
}
