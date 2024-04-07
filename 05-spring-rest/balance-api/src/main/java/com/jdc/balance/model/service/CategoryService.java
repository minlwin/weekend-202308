package com.jdc.balance.model.service;

import static com.jdc.balance.model.Commons.getOne;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jdc.balance.api.input.CategoryForm;
import com.jdc.balance.api.input.CategorySearch;
import com.jdc.balance.api.output.CategoryInfo;
import com.jdc.balance.model.entity.Category;
import com.jdc.balance.model.repo.CategoryRepo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
@Transactional
public class CategoryService {
	
	private static final String DOMAIN = "Category";
	
	@Autowired
	private CategoryRepo repo;

	public CategoryInfo create(CategoryForm form) {
		var entity = repo.save(form.getEntity());
		return CategoryInfo.from(entity);
	}

	public CategoryInfo update(int id, CategoryForm form) {
		var entity = getOne(repo.findById(id), DOMAIN, id);
		entity.setName(form.name());
		entity.setType(form.type());
		entity.setDescription(form.description());
		return CategoryInfo.from(entity);
	}

	@Transactional(readOnly = true)
	public CategoryInfo findById(int id) {
		return getOne(repo.findById(id).map(CategoryInfo::from), DOMAIN, id);
	}

	@Transactional(readOnly = true)
	public List<CategoryInfo> search(CategorySearch search) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Function<CriteriaBuilder, CriteriaQuery<CategoryInfo>> queryFunc(CategorySearch search) {
		return cb -> {
			var cq = cb.createQuery(CategoryInfo.class);
			var root = cq.from(Category.class);
			CategoryInfo.select(cq, root);
			cq.where(search.where(cb, root));
			return cq;
		};
	}
	public List<CategoryInfo> upload(MultipartFile file) {
		// TODO Auto-generated method stub
		return null;
	}

}
