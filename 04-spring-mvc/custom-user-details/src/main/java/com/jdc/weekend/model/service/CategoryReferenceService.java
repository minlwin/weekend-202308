package com.jdc.weekend.model.service;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.weekend.model.entity.Category;
import com.jdc.weekend.model.entity.Category_;
import com.jdc.weekend.model.output.CategoryInfo;
import com.jdc.weekend.model.repo.CategoryRepo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
public class CategoryReferenceService {

	@Autowired
	private CategoryRepo repo;
	
	@Transactional(readOnly = true)
	public List<CategoryInfo> findActiveCategories() {
		
		Function<CriteriaBuilder, CriteriaQuery<CategoryInfo>> queryFun = cb -> {
			var cq = cb.createQuery(CategoryInfo.class);
			var root = cq.from(Category.class);
			CategoryInfo.select(cq, root);
			
			cq.where(cb.isFalse(root.get(Category_.deleted)));
			cq.orderBy(cb.asc(root.get(Category_.name)));
			
			return cq;
		};
		
		return repo.search(queryFun);
	}
}
