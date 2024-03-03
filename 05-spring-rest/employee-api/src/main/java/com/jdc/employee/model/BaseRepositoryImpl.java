package com.jdc.employee.model;

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

public class BaseRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID>{
	
	private EntityManager em;
	
	public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.em = entityManager;
	}

	@Override
	public <R> List<R> search(Function<CriteriaBuilder, CriteriaQuery<R>> queryFunc) {
		var cq = queryFunc.apply(em.getCriteriaBuilder());
		return em.createQuery(cq).getResultList();
	}

	@Override
	public <R> Page<R> search(Function<CriteriaBuilder, CriteriaQuery<R>> queryFunc,
			Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc, int page, int size) {

		var countQuery = countFunc.apply(em.getCriteriaBuilder());
		var count = em.createQuery(countQuery).getSingleResult();
		
		var pageable = PageRequest.of(page, size);
		var cq = queryFunc.apply(em.getCriteriaBuilder());
		
		var query = em.createQuery(cq);
		query.setFirstResult(page * size);
		query.setMaxResults(size);
		
		return new PageImpl<R>(query.getResultList(), pageable, count);
	}

}
