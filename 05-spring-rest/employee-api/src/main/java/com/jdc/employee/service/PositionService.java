package com.jdc.employee.service;

import static com.jdc.employee.model.DataModificationResults.createResult;
import static com.jdc.employee.model.DataModificationResults.updateResult;
import static com.jdc.employee.model.NullSafeHelper.safeCalll;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.employee.api.input.PositionCreateForm;
import com.jdc.employee.api.input.PositionSearch;
import com.jdc.employee.api.input.PositionUpdateForm;
import com.jdc.employee.api.output.PositionInfo;
import com.jdc.employee.api.output.PositionInfoDetails;
import com.jdc.employee.model.DataModificationResult;
import com.jdc.employee.model.entity.Position;
import com.jdc.employee.model.repo.PositionRepo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
@Transactional(readOnly = true)
public class PositionService {
	
	private static final String DOMAIN_NAME = "position";
	
	@Autowired
	private PositionRepo repo;

	@Transactional
	public DataModificationResult<String> create(PositionCreateForm form) {
		var entity = repo.save(form.entity());
		return createResult(entity.getCode(), DOMAIN_NAME, "code");
	}

	@Transactional
	public DataModificationResult<String> update(String code, PositionUpdateForm form) {
		var entity = safeCalll(repo.findById(code), DOMAIN_NAME, code);
		entity.setName(form.name());
		entity.setBasicSalary(form.basicSalary());
		entity.setOtPerHour(form.otPerHour());
		entity.setRemark(form.remark());
		return updateResult(code, DOMAIN_NAME, "code");
	}

	public PositionInfoDetails findById(String code) {
		var entity = safeCalll(repo.findById(code), DOMAIN_NAME, code);
		return PositionInfoDetails.from(entity);
	}

	public List<PositionInfo> search(PositionSearch search) {
		return repo.search(queryFunc(search));
	}

	private Function<CriteriaBuilder, CriteriaQuery<PositionInfo>> queryFunc(PositionSearch search) {
		return cb -> {
			var cq = cb.createQuery(PositionInfo.class);
			var root = cq.from(Position.class);
			
			PositionInfo.select(cb, cq, root);
			cq.where(search.where(cb, root));
			
			return cq;
		};
	}

}
