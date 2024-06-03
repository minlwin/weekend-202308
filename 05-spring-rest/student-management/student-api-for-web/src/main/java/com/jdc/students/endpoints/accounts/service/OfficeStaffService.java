package com.jdc.students.endpoints.accounts.service;

import static com.jdc.students.utils.RepositoryUtils.getOne;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.students.domain.account.AccountCodeGenerator;
import com.jdc.students.domain.account.entity.Account.Role;
import com.jdc.students.domain.account.entity.OfficeStaff;
import com.jdc.students.domain.account.entity.OfficeStaff_;
import com.jdc.students.domain.account.repo.AccountRepo;
import com.jdc.students.domain.account.repo.OfficeStaffRepo;
import com.jdc.students.endpoints.accounts.input.OfficeStaffForm;
import com.jdc.students.endpoints.accounts.input.OfficeStaffSearch;
import com.jdc.students.endpoints.accounts.output.OfficeStaffInfo;
import com.jdc.students.utils.dto.PageInfo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
@Transactional(readOnly = true)
public class OfficeStaffService {
	
	@Autowired
	private OfficeStaffRepo repo;
	
	@Autowired
	private AccountCodeGenerator accountCodeGenerator;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Value("${app.account.password.default}")
	private String defaultPassword;
	
	@Autowired
	private AccountRepo accountRepo;
	
	private static final String NO_ENTITY = "There is no Office Staff with code no %s.";

	@Transactional(isolation = Isolation.SERIALIZABLE)
	public OfficeStaffInfo create(OfficeStaffForm form) {
		
		var entity = form.entity();
		var account = entity.getAccount();
		
		account.setCode(accountCodeGenerator.next(Role.Office));
		account.setPassword(passwordEncoder.encode(defaultPassword));
		
		account = accountRepo.saveAndFlush(account);
		entity.setAccount(account);
		
		entity = repo.saveAndFlush(entity);
		
		return OfficeStaffInfo.from(entity);
	}
	
	@Transactional
	public OfficeStaffInfo update(String code, OfficeStaffForm form) {
		
		var entity = getOne(repo.findById(code), NO_ENTITY.formatted(code));

		entity.setPhone(form.phone());
		entity.setEmail(form.email());
		
		entity.setStatus(form.status());
		
		switch(form.status()) {
		case PreProvation -> entity.setAssignAt(form.statusChageAt());
		case Provation -> entity.setProvationAt(form.statusChageAt());
		case Resigned -> entity.setResignAt(form.statusChageAt());
		}
		
		entity.getAccount().setFullName(form.name());
		
		return OfficeStaffInfo.from(entity);
	}

	public OfficeStaffInfo findById(String code) {
		return getOne(repo.findById(code).map(OfficeStaffInfo::from), NO_ENTITY.formatted(code));
	}

	public PageInfo<OfficeStaffInfo> search(OfficeStaffSearch form, int page, int size) {
		return repo.search(queryFunc(form), countFunc(form), page, size);
	}
	
	private Function<CriteriaBuilder, CriteriaQuery<OfficeStaffInfo>> queryFunc(OfficeStaffSearch form) {
		return cb -> {
			var cq = cb.createQuery(OfficeStaffInfo.class);
			var root = cq.from(OfficeStaff.class);
			
			OfficeStaffInfo.select(cq, root);
			cq.where(form.where(cb, root));
			
			return cq;
		};
	}

	private Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc(OfficeStaffSearch form) {
		return cb -> {
			var cq = cb.createQuery(Long.class);
			var root = cq.from(OfficeStaff.class);
			cq.select(cb.count(root.get(OfficeStaff_.code)));
			cq.where(form.where(cb, root));
			
			return cq;
		};
	}

}
