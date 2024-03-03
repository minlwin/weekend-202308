package com.jdc.employee.service;

import static com.jdc.employee.model.NullSafeHelper.safeCalll;
import static com.jdc.employee.model.DataModificationResult.*;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.employee.api.input.DepartmentCreateForm;
import com.jdc.employee.api.input.DepartmentSearch;
import com.jdc.employee.api.input.DepartmentUpdateForm;
import com.jdc.employee.api.output.DepartmentInfo;
import com.jdc.employee.api.output.DepartmentInfoDetails;
import com.jdc.employee.model.DataModificationResult;
import com.jdc.employee.model.entity.Department;
import com.jdc.employee.model.entity.Department_;
import com.jdc.employee.model.repo.DepartmentRepo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
@Transactional(readOnly = true)
public class DepartmentService {
	
	private static final String DOMAIN_NAME = "Department";
	
	@Autowired
	private DepartmentRepo repo;

	public List<DepartmentInfo> search(DepartmentSearch search) {
		return repo.search(queryFun(search));
	}
	
	private Function<CriteriaBuilder, CriteriaQuery<DepartmentInfo>> queryFun(DepartmentSearch search) {
		return cb -> {
			var cq = cb.createQuery(DepartmentInfo.class);
			var root = cq.from(Department.class);
			DepartmentInfo.select(cb, cq, root);
			cq.where(search.where(cb, root));
			cq.orderBy(cb.asc(root.get(Department_.code)));
			return cq;
		};
	}	

	@Transactional
	public DataModificationResult<String> create(DepartmentCreateForm form) {
		var entity = repo.save(form.entity());
		return  createResult(entity.getCode(), DOMAIN_NAME, "code");
	}

	public DepartmentInfoDetails findById(String code) {
		return safeCalll(repo.findById(code)
				.map(DepartmentInfoDetails::from), DOMAIN_NAME, code);
	}
	
	@Transactional
	public DataModificationResult<String> update(String code, DepartmentUpdateForm form) {
		var entity = safeCalll(repo.findById(code), DOMAIN_NAME, code);
		entity.setName(form.name());
		entity.setPhone(form.phone());
		entity.setDescription(form.description());
		return updateResult(entity.getCode(), DOMAIN_NAME, "code");
	}

}
