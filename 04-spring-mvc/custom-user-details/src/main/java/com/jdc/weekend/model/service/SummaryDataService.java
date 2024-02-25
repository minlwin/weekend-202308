package com.jdc.weekend.model.service;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.weekend.model.entity.Category_;
import com.jdc.weekend.model.entity.Member_;
import com.jdc.weekend.model.entity.Post;
import com.jdc.weekend.model.entity.Post_;
import com.jdc.weekend.model.repo.PostRepo;
import com.jdc.weekend.rest.output.NameAndCount;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
@Transactional(readOnly = true)
public class SummaryDataService {
	
	@Autowired
	private PostRepo repo;
	
	private static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("yyyy/MM/dd");

	public List<NameAndCount> findTopUsers(int size) {
		return repo.search(topUserQuery, size);
	}

	public List<NameAndCount> findCategoryData() {
		return repo.search(categoryQuery);
	}
	
	public List<Integer> getBusinessYears() {
		
		var result = new ArrayList<Integer>();
		// Get first date of post
		var minYear = repo.searchOne(dateSearch(true)).orElse(LocalDateTime.now())
				.getYear();
		
		// Get Last date of post
		var maxYear = repo.searchOne(dateSearch(false)).orElse(LocalDateTime.now())
				.getYear();
		
		var target = minYear;
		
		while(target <= maxYear) {
			result.add(target ++);
		}
		
		return result;
	}
	
	private static Function<CriteriaBuilder, CriteriaQuery<LocalDateTime>> dateSearch(boolean first) {
		return cb -> {
			var cq = cb.createQuery(LocalDateTime.class);
			var root = cq.from(Post.class);
			var selectColumn = first ? cb.least(root.get(Post_.postAt)) : 
				cb.greatest(root.get(Post_.postAt));
			
			cq.select(selectColumn);
			return cq;
		};
	}
	
	private static Function<CriteriaBuilder, CriteriaQuery<NameAndCount>> categoryQuery = cb -> {
		// select p.category.name, count(p.id) posts from Post p group by p.category.name order by posts desc
		var cq = cb.createQuery(NameAndCount.class);
		
		var root = cq.from(Post.class);
		var count = cb.count(root.get(Post_.id));
		
		cq.multiselect(
			root.get(Post_.category).get(Category_.name),
			count
		);
		
		cq.groupBy(
			root.get(Post_.category).get(Category_.name)
		);
		
		return cq;
	};	

	
	private static Function<CriteriaBuilder, CriteriaQuery<NameAndCount>> topUserQuery = cb -> {
		// select p.owner.name, count(p.id) posts from Post p group by p.owner.name order by posts desc
		var cq = cb.createQuery(NameAndCount.class);
		
		var root = cq.from(Post.class);
		var count = cb.count(root.get(Post_.id));
		
		cq.multiselect(
			root.get(Post_.owner).get(Member_.name),
			count
		);
		
		cq.groupBy(
			root.get(Post_.owner).get(Member_.name)
		);
		
		cq.orderBy(
			cb.desc(count)	
		);
		
		return cq;
	};

	public List<NameAndCount> search(int year) {
		
		var result = new ArrayList<NameAndCount>();
		
		for(var month : Month.values()) {
			var target = YearMonth.of(year, month);
			var from = target.atDay(1).atStartOfDay();
			var to = target.atEndOfMonth().plusDays(1).atStartOfDay();
			
			long count = repo.countForRange(from, to);
			result.add(new NameAndCount(month.name(), count));
		}
		
		return result;
	}

	public List<NameAndCount> search(int year, Month month) {
		var yearMonth = YearMonth.of(year, month);
		var target = yearMonth.atDay(1);
		var lastDay = yearMonth.atEndOfMonth();

		var result = new ArrayList<NameAndCount>();
	
		while(target.compareTo(lastDay) <= 0) {
			var from = target.atStartOfDay();
			var to = target.plusDays(1).atStartOfDay();
			long count = repo.countForRange(from, to);
			result.add(new NameAndCount(target.format(DF), count));

			target = target.plusDays(1);
		}
		return result;
	}
}
