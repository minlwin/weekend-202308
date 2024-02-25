package com.jdc.weekend.model.repo;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.Query;

import com.jdc.weekend.model.BaseRepository;
import com.jdc.weekend.model.entity.Post;

public interface PostRepo extends BaseRepository<Post, Integer>{

	@Query("select count(p.id) from Post p where p.postAt >= ?1 and p.postAt < ?2")
	long countForRange(LocalDateTime from, LocalDateTime to);
}
