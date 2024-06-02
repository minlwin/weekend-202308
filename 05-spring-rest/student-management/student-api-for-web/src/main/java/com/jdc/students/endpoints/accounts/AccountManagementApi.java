package com.jdc.students.endpoints.accounts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.students.endpoints.accounts.input.AccountSearch;
import com.jdc.students.endpoints.accounts.output.AccountInfo;
import com.jdc.students.endpoints.accounts.service.AccountService;

@RestController
@RequestMapping("admin/accounts")
public class AccountManagementApi {
	
	@Autowired
	private AccountService service;

	@GetMapping
	public List<AccountInfo> search(AccountSearch form) {
		return service.search(form);
	}
}
