package com.jdc.weekend.model.service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jdc.weekend.model.entity.Category_;
import com.jdc.weekend.model.entity.Post;
import com.jdc.weekend.model.entity.Post_;
import com.jdc.weekend.model.output.PostInfo;
import com.jdc.weekend.model.output.PostInfoDetails;
import com.jdc.weekend.model.repo.PostRepo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
@Transactional(readOnly = true)
public class PublicPostService {
	
	@Autowired
	private PostRepo postRepo;
	
	public Page<PostInfo> search(Optional<Integer> category, Optional<String> keyword, int page, int size) {
		
		Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc = cb -> {
			var cq = cb.createQuery(Long.class);
			var root = cq.from(Post.class);
			cq.select(cb.count(root.get(Post_.id)));
			cq.where(where(cb, root, category, keyword));
			return cq;
		};
		
		Function<CriteriaBuilder, CriteriaQuery<PostInfo>> queryFunc = cb -> {
			var cq = cb.createQuery(PostInfo.class);
			var root = cq.from(Post.class);
			PostInfo.select(cb, cq, root);
			cq.where(where(cb, root, category, keyword));
			cq.orderBy(cb.desc(root.get(Post_.postAt)));
			return cq;
		};
		
		return postRepo.search(queryFunc, countFunc, page, size);
	}

	public PostInfoDetails findById(int id) {
		return postRepo.findById(id).map(PostInfoDetails::from).orElseThrow();
	}
	
	private Predicate[] where(CriteriaBuilder cb, Root<Post> root, Optional<Integer> category, Optional<String> keyword) {
		var list = new ArrayList<Predicate>();
		
		category.filter(param -> param > 0).ifPresent(param -> {
			list.add(cb.equal(root.get(Post_.category).get(Category_.id), param));
		});
		
		keyword.filter(StringUtils::hasLength).ifPresent(param -> {
			list.add(cb.like(cb.lower(root.get(Post_.title)), param.toLowerCase().concat("%")));
		});
		
		return list.toArray(size -> new Predicate[size]);
	}

}
