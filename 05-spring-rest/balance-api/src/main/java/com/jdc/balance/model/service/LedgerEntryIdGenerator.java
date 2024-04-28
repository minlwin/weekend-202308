package com.jdc.balance.model.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.balance.model.entity.LedgerEntryPk;
import com.jdc.balance.model.entity.LedgerEntrySeq;
import com.jdc.balance.model.repo.LedgerEntrySeqRepo;

@Service
public class LedgerEntryIdGenerator {

	@Autowired
	private LedgerEntrySeqRepo repo;
	
	@Transactional
	public LedgerEntryPk next(LocalDate date) {
		var id = repo.findById(date)
			.orElseGet(() -> repo.save(new LedgerEntrySeq(date, 0)));
		
		return id.next();
	}
}
