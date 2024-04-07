package com.jdc.balance.model.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.jdc.balance.api.input.LedgerEntryForm;
import com.jdc.balance.api.input.LedgerEntrySearch;
import com.jdc.balance.api.output.LedgerEntryInfo;
import com.jdc.balance.api.output.LedgerEntryInfoDetails;

@Service
public class LedgerEntryService {

	public Page<LedgerEntryInfo> search(LedgerEntrySearch search, int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	public LedgerEntryInfo create(LedgerEntryForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	public LedgerEntryInfo update(String id, LedgerEntryForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	public LedgerEntryInfoDetails findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
