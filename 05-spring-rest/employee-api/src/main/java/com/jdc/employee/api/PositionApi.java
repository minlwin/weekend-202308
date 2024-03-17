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

import com.jdc.employee.api.input.PositionCreateForm;
import com.jdc.employee.api.input.PositionSearch;
import com.jdc.employee.api.input.PositionUpdateForm;
import com.jdc.employee.api.output.PositionInfo;
import com.jdc.employee.api.output.PositionInfoDetails;
import com.jdc.employee.model.ApiResponse;
import com.jdc.employee.model.DataModificationResult;
import com.jdc.employee.service.PositionService;

@RestController
@RequestMapping("position")
public class PositionApi {
	
	@Autowired
	private PositionService service;

	@GetMapping
	ApiResponse<List<PositionInfo>> search(PositionSearch search) {
		return ApiResponse.success(service.search(search));
	}
	
	@PostMapping
	ApiResponse<DataModificationResult<String>> create(
			@Validated @RequestBody PositionCreateForm form, BindingResult result) {
		return ApiResponse.success(service.create(form));
	}
	
	@PutMapping("{code}")
	ApiResponse<DataModificationResult<String>> update(@PathVariable String code, 
			@Validated @RequestBody PositionUpdateForm form, BindingResult result) {
		return ApiResponse.success(service.update(code, form));
	}

	@GetMapping("{code}")
	ApiResponse<PositionInfoDetails> findById(@PathVariable String code) {
		return ApiResponse.success(service.findById(code));
	}
}
