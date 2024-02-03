package com.jdc.weekend.model.service;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.weekend.model.entity.Member;
import com.jdc.weekend.model.entity.Member_;
import com.jdc.weekend.model.input.MemberRoleUpdateForm;
import com.jdc.weekend.model.input.MemberSearch;
import com.jdc.weekend.model.input.MemberStatusForm;
import com.jdc.weekend.model.output.MemberInfo;
import com.jdc.weekend.model.output.MemberInfoDetails;
import com.jdc.weekend.model.repo.MemberRepo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
@Transactional(readOnly = true)
public class MemberManagementService {
	
	@Autowired
	private MemberRepo repo;

	public Page<MemberInfo> search(MemberSearch search, int page, int size) {
		
		// Count Query
		// select count(m.id) from Member m where [where clause]
		Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc = cb -> {

			var cq = cb.createQuery(Long.class);
			var root = cq.from(Member.class);
			
			cq.select(cb.count(root.get(Member_.id)));
			cq.where(search.where(cb, root));
			
			return cq;
		};
		
		// Select Query
		Function<CriteriaBuilder, CriteriaQuery<MemberInfo>> queryFunc = cb -> {
			var cq = cb.createQuery(MemberInfo.class);
			var root = cq.from(Member.class);
			
			MemberInfo.select(cq, root);
			cq.where(search.where(cb, root));
			return cq;
		};
		
		return repo.search(queryFunc, countFunc, page, size);
	}

	public MemberInfoDetails findById(int id) {
		return repo.findById(id).map(MemberInfoDetails::from)
				.orElseThrow();
	}

	@Transactional
	@PreAuthorize("hasAuthority('Admin')")
	public void update(int id, MemberRoleUpdateForm form) {
		repo.findById(id).ifPresent(member -> {
			member.setRole(form.role());
		});
	}

	@Transactional
	@PreAuthorize("hasAuthority('Admin')")
	public void update(int id, MemberStatusForm form) {
		repo.findById(id).ifPresent(member -> {
			member.setDeleted(form.deleted());
		});
	}

}
