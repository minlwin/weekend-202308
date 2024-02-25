package com.jdc.weekend.model;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

public class BaseRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID>{
	
	private EntityManager entityManager;

	public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityManager = entityManager;
	}

	@Override
	public <R> Optional<R> searchOne(Function<CriteriaBuilder, CriteriaQuery<R>> queryFunc) {
		
		var criteriaQuery = queryFunc.apply(entityManager.getCriteriaBuilder());
		
		var query = entityManager.createQuery(criteriaQuery);
		
		return Optional.ofNullable(query.getSingleResult());
	}

	@Override
	public <R> List<R> search(Function<CriteriaBuilder, CriteriaQuery<R>> queryFunc) {
		
		var criteriaQuery = queryFunc.apply(entityManager.getCriteriaBuilder());
		
		var query = entityManager.createQuery(criteriaQuery);

		return query.getResultList();
	}

	@Override
	public <R> List<R> search(Function<CriteriaBuilder, CriteriaQuery<R>> queryFunc, int limit) {
		var criteriaQuery = queryFunc.apply(entityManager.getCriteriaBuilder());
		
		var query = entityManager.createQuery(criteriaQuery);
		
		query.setMaxResults(limit);
		
		return query.getResultList();
	}

	@Override
	public <R> Page<R> search(Function<CriteriaBuilder, CriteriaQuery<R>> queryFunc,
			Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc, int page, int size) {
		
		var total = searchOne(countFunc).orElse(0L);
		var pageable = PageRequest.of(page, size);
		
		var criteriaQuery = queryFunc.apply(entityManager.getCriteriaBuilder());
		
		var query = entityManager.createQuery(criteriaQuery);
		query.setFirstResult(page * size);
		query.setMaxResults(size);
		
		var content = query.getResultList();

		return new PageImpl<R>(content, pageable, total);
	}


}
