package com.jdc.students.endpoints.accounts.service;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.students.domain.account.entity.Account;
import com.jdc.students.domain.account.entity.Account_;
import com.jdc.students.domain.account.repo.AccountRepo;
import com.jdc.students.endpoints.accounts.input.AccountSearch;
import com.jdc.students.endpoints.accounts.output.AccountInfo;
import com.jdc.students.utils.dto.PageInfo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepo repo;

	@Transactional(readOnly = true)
	public PageInfo<AccountInfo> search(AccountSearch form, int page, int size) {
		return repo.search(queryFunc(form), countFunc(form), page, size);
	}
	
	private Function<CriteriaBuilder, CriteriaQuery<AccountInfo>> queryFunc(AccountSearch form) {
		return cb -> {
			var cq = cb.createQuery(AccountInfo.class);
			var root = cq.from(Account.class);
			
			AccountInfo.select(cq, root);
			cq.where(form.where(cb, root));
			
			return cq;
		};
	}

	private Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc(AccountSearch form) {
		return cb -> {
			var cq = cb.createQuery(Long.class);
			var root = cq.from(Account.class);
			cq.select(cb.count(root.get(Account_.code)));
			cq.where(form.where(cb, root));
			
			return cq;
		};
	}
}
