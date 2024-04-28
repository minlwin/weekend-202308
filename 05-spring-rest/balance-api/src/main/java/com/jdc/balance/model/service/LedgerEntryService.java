package com.jdc.balance.model.service;

import static com.jdc.balance.model.Commons.getOne;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.balance.api.input.LedgerEntryForm;
import com.jdc.balance.api.input.LedgerEntrySearch;
import com.jdc.balance.api.output.LedgerEntryInfo;
import com.jdc.balance.api.output.LedgerEntryInfoDetails;
import com.jdc.balance.model.ApiBusinessException;
import com.jdc.balance.model.Role;
import com.jdc.balance.model.entity.LedgerEntry;
import com.jdc.balance.model.entity.LedgerEntryPk;
import com.jdc.balance.model.entity.LedgerEntryPk_;
import com.jdc.balance.model.entity.LedgerEntry_;
import com.jdc.balance.model.repo.CategoryRepo;
import com.jdc.balance.model.repo.LedgerEntryRepo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
@Transactional(readOnly = true)
public class LedgerEntryService {
	
	private static final String DOMAIN_NAME = "Ledger Entry";

	private static final String ACCOUNT_DOMAIN = "Account";

	private static final String CATEGORY_DOMAIN = "Category";
	
	@Autowired
	private LedgerEntryRepo repo;
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private LoginUserService loginUserService;
	
	@Autowired
	private LedgerEntryIdGenerator idGenerator;

	@Transactional
	public LedgerEntryInfo create(LedgerEntryForm form) {
		
		var entity = form.entity();
		var account = getOne(loginUserService.getLoginAccount(), ACCOUNT_DOMAIN, loginUserService.getLoginId().orElse("No User"));
		var category = getOne(categoryRepo.findById(form.category()), CATEGORY_DOMAIN, form.category());
		
		entity.setAccount(account);
		entity.setCategory(category);
		entity.setId(idGenerator.next(form.issueAt()));
		
		entity = repo.save(entity);
		
		return LedgerEntryInfo.from(entity);
	}

	@Transactional
	public LedgerEntryInfo update(String id, LedgerEntryForm form) {
		
		var entity = getOne(repo.findById(LedgerEntryPk.parse(id)), DOMAIN_NAME, id);
		var account = getOne(loginUserService.getLoginAccount(), ACCOUNT_DOMAIN, loginUserService.getLoginId().orElse("No User"));
		
		if(account.getRole() == Role.Employee && entity.getAccount().getId() != account.getId()) {
			throw new ApiBusinessException("%s has no permission to update this ledger entry.".formatted(account.getName()));
		}
		
		if(!entity.getId().getIssueDate().equals(form.issueAt())) {
			throw new ApiBusinessException("Invalid issue at.");
		}

		var category = getOne(categoryRepo.findById(form.category()), CATEGORY_DOMAIN, form.category());
		
		entity.setCategory(category);
		entity.setRemark(form.remark());
		entity.setItems(form.getItemsForDb());
		
		return LedgerEntryInfo.from(entity);
	}

	public LedgerEntryInfoDetails findById(String id) {
		return getOne(repo.findById(LedgerEntryPk.parse(id))
				.map(LedgerEntryInfoDetails::from), DOMAIN_NAME, id);
	}

	public Page<LedgerEntryInfo> search(LedgerEntrySearch search, int page, int size) {
		return repo.search(queryFun(search), countFunc(search), page, size);
	}
	
	private Function<CriteriaBuilder, CriteriaQuery<LedgerEntryInfo>> queryFun(LedgerEntrySearch search) {
		return cb -> {
			var cq = cb.createQuery(LedgerEntryInfo.class);
			var root = cq.from(LedgerEntry.class);
			LedgerEntryInfo.select(cb, cq, root);
			cq.where(search.where(cb, root));
			cq.orderBy(
				cb.asc(root.get(LedgerEntry_.id).get(LedgerEntryPk_.issueDate)),
				cb.asc(root.get(LedgerEntry_.id).get(LedgerEntryPk_.seqNumber))
			);
			return cq;
		};
	}
	
	private Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc(LedgerEntrySearch search) {
		return cb -> {
			var cq = cb.createQuery(Long.class);
			var root = cq.from(LedgerEntry.class);
			cq.select(cb.count(root.get(LedgerEntry_.id)));
			cq.where(search.where(cb, root));
			return cq;
		};
	}	

}
