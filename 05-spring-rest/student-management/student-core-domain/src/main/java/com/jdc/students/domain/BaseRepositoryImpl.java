package com.jdc.students.domain;

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

	private EntityManager em;
	
	public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.em = entityManager;
	}

	@Override
	public <R> List<R> search(Function<CriteriaBuilder, CriteriaQuery<R>> queryFunc) {
		var query = em.createQuery(queryFunc.apply(em.getCriteriaBuilder()));
		return query.getResultList();
	}

	@Override
	public <R> Page<R> search(Function<CriteriaBuilder, CriteriaQuery<R>> queryFunc,
			Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc, int page, int size) {
		var countQuery = em.createQuery(countFunc.apply(em.getCriteriaBuilder()));
		var count = countQuery.getSingleResult();
		
		var contentQuery = em.createQuery(queryFunc.apply(em.getCriteriaBuilder()));
		contentQuery.setFirstResult(size * page);
		contentQuery.setMaxResults(size);
		
		return new PageImpl<R>(contentQuery.getResultList(), PageRequest.of(page, size), count);
	}

	@Override
	public Long count(Function<CriteriaBuilder, CriteriaQuery<Long>> queryFunc) {
		var query = em.createQuery(queryFunc.apply(em.getCriteriaBuilder()));
		return query.getSingleResult();
	}

	@Override
	public <R> Optional<R> findOne(Function<CriteriaBuilder, CriteriaQuery<R>> queryFunc) {
		var query = em.createQuery(queryFunc.apply(em.getCriteriaBuilder()));
		return Optional.ofNullable(query.getSingleResult());
	}

}
