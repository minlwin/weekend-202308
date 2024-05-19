package com.jdc.balance.model.service;

import static com.jdc.balance.model.Commons.getOne;

import java.time.LocalDateTime;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.balance.api.input.ChangePasswordForm;
import com.jdc.balance.api.input.EmployeeForm;
import com.jdc.balance.api.input.EmployeeSearch;
import com.jdc.balance.api.input.EmployeeStatusForm;
import com.jdc.balance.api.input.ProfileForm;
import com.jdc.balance.api.output.ChangePassResult;
import com.jdc.balance.api.output.EmployeeInfo;
import com.jdc.balance.api.output.EmployeeInfoDetails;
import com.jdc.balance.api.output.ProfileInfo;
import com.jdc.balance.model.EmployeeChanges;
import com.jdc.balance.model.Status;
import com.jdc.balance.model.entity.Account_;
import com.jdc.balance.model.entity.Employee;
import com.jdc.balance.model.entity.Employee_;
import com.jdc.balance.model.events.EmployeeChangeEvent;
import com.jdc.balance.model.exceptions.ApiBusinessException;
import com.jdc.balance.model.repo.EmployeeRepo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
@Transactional(readOnly = true)
public class EmployeeService {
	
	private static final String DOMAIN_NAME = "Employee";
	
	@Autowired
	private EmployeeRepo repo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ApplicationEventPublisher eventPublisher;

	public EmployeeInfoDetails findById(int id) {
		return getOne(
				repo.findById(id).map(EmployeeInfoDetails::from), 
				DOMAIN_NAME, id);
	}

	public EmployeeForm findByIdForEdit(int id) {
		return getOne(repo.findById(id).map(EmployeeForm::from), 
				DOMAIN_NAME, id);
	}

	@Transactional
	public EmployeeInfo create(EmployeeForm form) {
		var entity = form.entity(passwordEncoder);
		
		entity = repo.saveAndFlush(entity);
		
		eventPublisher.publishEvent(new EmployeeChangeEvent(EmployeeChanges.Creation, entity.getId()));
		
		return EmployeeInfo.from(entity);
	}

	@Transactional
	public EmployeeInfo update(int id, EmployeeForm form) {
		
		var entity = getOne(repo.findById(id), DOMAIN_NAME, id);
		entity.getAccount().setName(form.name());
		entity.getAccount().setRole(form.role());
		entity.setPhone(form.phone());
		entity.setEmail(form.email());

		eventPublisher.publishEvent(new EmployeeChangeEvent(EmployeeChanges.InfoChange, entity.getId()));
		
		return EmployeeInfo.from(entity);
	}

	@Transactional
	public EmployeeInfo update(int id, EmployeeStatusForm form) {
		var entity = getOne(repo.findById(id), DOMAIN_NAME, id);
		
		entity.setStatus(form.status());
		entity.setStatusChangeAt(LocalDateTime.now());
		
		eventPublisher.publishEvent(new EmployeeChangeEvent(EmployeeChanges.StatusChange, entity.getId()));
		
		return EmployeeInfo.from(entity);
	}
	
	public Page<EmployeeInfo> search(EmployeeSearch search, int page, int size) {
		return repo.search(queryFunc(search), countFunc(search), page, size);
	}

	private Function<CriteriaBuilder, CriteriaQuery<EmployeeInfo>> queryFunc(EmployeeSearch search) {
		return cb -> {
			var cq = cb.createQuery(EmployeeInfo.class);
			var root = cq.from(Employee.class);
			EmployeeInfo.select(cq, root);
			cq.where(search.where(cb, root));
			cq.orderBy(cb.asc(root.get(Employee_.account).get(Account_.name)));
			return cq;
		};
	}

	private Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc(EmployeeSearch search) {
		return cb -> {
			var cq = cb.createQuery(Long.class);
			var root = cq.from(Employee.class);
			cq.select(cb.count(root.get(Employee_.id)));
			cq.where(search.where(cb, root));
			return cq;
		};
	}

	@Transactional
	public ChangePassResult changePassword(ChangePasswordForm form) {
		
		var employee = getOne(repo.findOneByAccountLoginId(form.loginId()), DOMAIN_NAME, form.loginId());
		
		if(!passwordEncoder.matches(form.oldPassword(), employee.getAccount().getPassword())) {
			throw new ApiBusinessException("Invalid old password.");
		}
		
		employee.getAccount().setPassword(passwordEncoder.encode(form.newPassword()));
		
		if(employee.getStatus() == Status.Applied) {
			employee.setStatus(Status.Activated);
			eventPublisher.publishEvent(new EmployeeChangeEvent(EmployeeChanges.StatusChange, employee.getId()));
		}
		
		return new ChangePassResult(employee.getAccount().getRole().name());
	}

	public ProfileInfo findProfile(String loginId) {
		return getOne(repo.findOneByAccountLoginId(loginId)
				.map(ProfileInfo::from), DOMAIN_NAME, loginId);
	}

	@Transactional
	public ProfileInfo updateProfile(ProfileForm form) {
		var employee = getOne(repo.findOneByAccountLoginId(form.loginId()), DOMAIN_NAME, form.loginId());
		employee.setPhone(form.phone());
		employee.getAccount().setName(form.name());
		
		eventPublisher.publishEvent(new EmployeeChangeEvent(EmployeeChanges.InfoChange, employee.getId()));
		
		return ProfileInfo.from(employee);
	}
}
