package com.jdc.weekend.model.service;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.weekend.model.entity.Category;
import com.jdc.weekend.model.input.CategoryForm;
import com.jdc.weekend.model.input.CategorySearch;
import com.jdc.weekend.model.output.CategoryInfoDetails;
import com.jdc.weekend.model.repo.CategoryRepo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
@Transactional
public class CategoryManagementService {
	
	@Autowired
	private CategoryRepo repo;

	@Transactional(readOnly = true)
	public List<CategoryInfoDetails> search(CategorySearch search) {
		
		Function<CriteriaBuilder, CriteriaQuery<CategoryInfoDetails>> queryFunc = cb -> {
			var cq = cb.createQuery(CategoryInfoDetails.class);
			var root = cq.from(Category.class);
			
			CategoryInfoDetails.select(cb, cq, root);
			cq.where(search.where(cb, root));
			
			return cq;
		};
		
		return repo.search(queryFunc);
	}

	public void save(CategoryForm form) {
		repo.save(form.entity());
	}

	public void save(int id, CategoryForm form) {
		var entity = repo.findById(id).orElseThrow();
		entity.setName(form.name());
		entity.setDescription(form.description());
		entity.setDeleted(form.deleted());
	}

}
