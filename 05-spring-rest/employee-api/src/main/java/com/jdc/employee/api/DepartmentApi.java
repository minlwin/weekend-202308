package com.jdc.employee.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.employee.api.input.DepartmentCreateForm;
import com.jdc.employee.api.input.DepartmentSearch;
import com.jdc.employee.api.input.DepartmentUpdateForm;
import com.jdc.employee.api.output.DepartmentInfo;
import com.jdc.employee.api.output.DepartmentInfoDetails;
import com.jdc.employee.model.ApiResponse;
import com.jdc.employee.model.DataModificationResult;
import com.jdc.employee.service.DepartmentService;

@RestController
@RequestMapping("department")
public class DepartmentApi {
	
	@Autowired
	private DepartmentService service;

	@GetMapping
	ApiResponse<List<DepartmentInfo>> search(DepartmentSearch search) {
		return ApiResponse.success(service.search(search));
	}
	
	@PostMapping
	ApiResponse<DataModificationResult<String>> create(
			@Validated @RequestBody DepartmentCreateForm form, BindingResult result) {
		return ApiResponse.success(service.create(form));
	}
	
	@GetMapping("{code}")
	ApiResponse<DepartmentInfoDetails> findById(@PathVariable String code) {
		return ApiResponse.success(service.findById(code));
	}
	
	@PutMapping("{code}")
	ApiResponse<DataModificationResult<String>> update(@PathVariable String code,
			@Validated @RequestBody DepartmentUpdateForm form, BindingResult result) {
		return ApiResponse.success(service.update(code, form));
	}
	
}
