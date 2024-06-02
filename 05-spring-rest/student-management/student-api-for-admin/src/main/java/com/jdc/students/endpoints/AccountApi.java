package com.jdc.students.endpoints;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.students.input.AccountSearch;
import com.jdc.students.output.AccountInfo;
import com.jdc.students.service.AccountService;

@RestController
@RequestMapping("accounts")
public class AccountApi {
	
	@Autowired
	private AccountService service;

	@GetMapping
	public List<AccountInfo> search(AccountSearch form) {
		return service.search(form);
	}
}
