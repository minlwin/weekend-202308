package com.jdc.students.domain.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.students.domain.account.entity.Account.Role;
import com.jdc.students.domain.account.entity.AccountCodeSeq;
import com.jdc.students.domain.account.repo.AccountCodeSeqRepo;

@Service
public class AccountCodeGenerator {

	@Autowired
	private AccountCodeSeqRepo seqRepo;
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public String next(Role role) {
		
		var seq = seqRepo.findById(role)
			.orElseGet(() -> {
				var entity = new AccountCodeSeq();
				entity.setRole(role);
				return seqRepo.saveAndFlush(entity);
			});	
		
		return seq.next();
	}
}
