package com.jdc.balance.model.service;

import static com.jdc.balance.model.Commons.getOne;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.jdc.balance.api.input.CategoryForm;
import com.jdc.balance.api.input.CategorySearch;
import com.jdc.balance.api.output.CategoryInfo;
import com.jdc.balance.model.BalanceType;
import com.jdc.balance.model.entity.Category;
import com.jdc.balance.model.entity.Category_;
import com.jdc.balance.model.exceptions.ApiBusinessException;
import com.jdc.balance.model.repo.CategoryRepo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
@Transactional
public class CategoryService {
	
	private static final String DOMAIN = "Category";
	
	@Autowired
	private CategoryRepo repo;
	
	@Autowired
	private TextFileReadService fileReadService;

	public CategoryInfo create(CategoryForm form) {
		var entity = repo.save(form.entity());
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
		return repo.search(queryFunc(search));
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
		
		var list = fileReadService.read(file, "\t");
		
		validate(list);
		
		var entities = list.stream().map(Category::new).toList();
		
		return repo.saveAll(entities).stream()
				.map(CategoryInfo::from)
				.toList();
	}

	private void validate(List<String[]> list) {
		
		if(null == list || list.isEmpty()) {
			throw new ApiBusinessException("There is no data to upload.");
		}
		
		var messages = new ArrayList<String>();
		var balanceTypes = Stream.of(BalanceType.values())
				.map(a -> a.name()).toList();
		
		for(var i = 0; i < list.size(); i ++) {
			var array = list.get(i);
			
			if(array.length != 3) {
				messages.add("Line No. %d : Invalid data format.".formatted(i + 1));
			}
			
			if(array.length >= 1 && !balanceTypes.contains(array[0])) {
				messages.add("Line No. %d : Invalid balance type.".formatted(i + 1));
			}
			
			if(array.length >= 2 && !StringUtils.hasLength(array[1])) {
				messages.add("Line No. %d : There is no category name.".formatted(i + 1));
			}
			
			if(array.length >= 2 && balanceTypes.contains(array[0]) && isDuplicateEntry(array[0], array[1])) {
				messages.add("Line No. %d : %s is already registered.".formatted(i + 1, array[1]));
			}
		}
		
		if(!messages.isEmpty()) {
			throw new ApiBusinessException(messages);
		}
	}

	private boolean isDuplicateEntry(String type, String name) {
		
		Function<CriteriaBuilder, CriteriaQuery<Long>> queryFunc = cb -> {
			var cq = cb.createQuery(Long.class);
			var root = cq.from(Category.class);
			cq.select(cb.count(root.get(Category_.id)));
			cq.where(
				cb.equal(root.get(Category_.type), BalanceType.valueOf(type)),
				cb.equal(cb.lower(root.get(Category_.name)), name.toLowerCase()),
				cb.isFalse(root.get(Category_.deleted))
			);
			return cq;
		};
		
		return repo.count(queryFunc) > 0;
	}

}
