package com.jdc.balance.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
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

import com.jdc.balance.api.input.LedgerEntryForm;
import com.jdc.balance.api.input.LedgerEntrySearch;
import com.jdc.balance.api.output.LedgerEntryInfo;
import com.jdc.balance.api.output.LedgerEntryInfoDetails;
import com.jdc.balance.model.service.LedgerEntryService;

@RestController
@RequestMapping("ledger")
public class LedgerEntryApi {
	
	@Autowired
	private LedgerEntryService service;

	@GetMapping
	@PreAuthorize("hasAnyAuthority('Admin', 'Manager') or (hasAuthority('Employee') and #search.loginId == authentication.name)")
	Page<LedgerEntryInfo> search(LedgerEntrySearch search,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size) {
		return service.search(search, page, size);
	}
	
	@PostMapping
	@PreAuthorize("hasAnyAuthority('Admin', 'Manager', 'Employee')")
	LedgerEntryInfo create(
			@Validated @RequestBody LedgerEntryForm form, BindingResult result) {
		return service.create(form);
	}
	
	@PutMapping("{id}")
	@PostAuthorize("hasAnyAuthority('Admin', 'Manager') or (hasAuthority('Employee') and returnObject.issuerId == authentication.name)")
	LedgerEntryInfo update(@PathVariable String id,
			@Validated @RequestBody LedgerEntryForm form, BindingResult result) {
		return service.update(id, form);
	}
	
	@GetMapping("{id}")
	@PostAuthorize("hasAnyAuthority('Admin', 'Manager') or (hasAuthority('Employee') and returnObject.info.issuerId == authentication.name)")
	LedgerEntryInfoDetails findById(@PathVariable String id) {
		return service.findById(id);
	}
}
