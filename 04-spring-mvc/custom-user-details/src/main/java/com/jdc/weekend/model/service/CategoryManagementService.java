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
@Transactional(readOnly = true)
public class CategoryManagementService {
	
	@Autowired
	private CategoryRepo repo;

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
	
	public CategoryForm findEditFromById(int id) {
		return repo.findById(id).map(a -> {
			var form = new CategoryForm();
			form.setId(a.getId());
			form.setName(a.getName());
			form.setDescription(a.getDescription());
			form.setDeleted(a.isDeleted());
			return form;
		}).orElseThrow();
	}

	@Transactional
	public void save(CategoryForm form) {
		if(form.getId() == 0) {
			repo.save(form.entity());
		} else {
			repo.findById(form.getId()).ifPresent(entity -> {
				entity.setName(form.getName());
				entity.setDescription(form.getDescription());
				entity.setDeleted(form.isDeleted());
			});
		}
	}

}
