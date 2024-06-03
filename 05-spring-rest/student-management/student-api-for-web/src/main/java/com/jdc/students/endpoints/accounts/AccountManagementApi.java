package com.jdc.students.endpoints.accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.students.endpoints.accounts.input.AccountSearch;
import com.jdc.students.endpoints.accounts.output.AccountInfo;
import com.jdc.students.endpoints.accounts.service.AccountService;
import com.jdc.students.utils.dto.PageInfo;

@RestController
@RequestMapping("admin/accounts")
public class AccountManagementApi {
	
	@Autowired
	private AccountService service;

	@GetMapping
	public PageInfo<AccountInfo> search(AccountSearch form, 
			@RequestParam(required = false, defaultValue = "0") int page, 
			@RequestParam(required = false, defaultValue = "10") int size) {
		return service.search(form, page, size);
	}
}
