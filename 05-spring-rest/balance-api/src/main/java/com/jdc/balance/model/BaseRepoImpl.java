package com.jdc.balance.model;

import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

public class BaseRepoImpl<T, ID> extends SimpleJpaRepository<T, ID> implements BaseRepo<T, ID>{
	
	private EntityManager em;
	
	public BaseRepoImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.em = entityManager;
	}

	@Override
	public <R> List<R> search(Function<CriteriaBuilder, CriteriaQuery<R>> queryFunc) {
		var criteriaQuery = queryFunc.apply(em.getCriteriaBuilder());
		var query = em.createQuery(criteriaQuery);
		return query.getResultList();
	}

	@Override
	public <R> Page<R> search(Function<CriteriaBuilder, CriteriaQuery<R>> queryFunc,
			Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc, int page, int size) {
		
		var countQuery = countFunc.apply(em.getCriteriaBuilder());
		var count = em.createQuery(countQuery).getSingleResult();
		
		var criteriaQuery = queryFunc.apply(em.getCriteriaBuilder());
		var query = em.createQuery(criteriaQuery);
		query.setFirstResult(page * size);
		query.setMaxResults(size);
		
		var list = query.getResultList();
		
		return new PageImpl<R>(list, PageRequest.of(page, size), count);
	}

}
