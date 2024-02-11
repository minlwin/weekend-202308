package com.jdc.weekend.model.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.weekend.model.AppBusinessException;
import com.jdc.weekend.model.entity.Post;
import com.jdc.weekend.model.input.PostForm;
import com.jdc.weekend.model.repo.CategoryRepo;
import com.jdc.weekend.model.repo.MemberRepo;
import com.jdc.weekend.model.repo.PostRepo;

@Service
public class MemberPostService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private MemberRepo memberRepo;
	
	@Autowired
	private ImageStorageService storageService;
	
	@Autowired
	private CategoryRepo categoryRepo;

	@PreAuthorize("hasAuthority('Member')")
	@Transactional(rollbackFor = AppBusinessException.class)
	public int save(PostForm form) {
		
		if(form.getId() == 0) {
			return create(form);
		}
		
		// Update
		postRepo.findById(form.getId()).ifPresent(entity -> {
			entity.setTitle(form.getTitle());
			entity.setDescription(form.getDescription());
			
			var category = categoryRepo.findById(form.getCategoryId()).orElseThrow();
			entity.setCategory(category);
			
			if(form.getFiles() != null && !form.getFiles().isEmpty()) {
				// Delete previous photos
				storageService.delete(entity.getImages());
				entity.getImages().clear();
				postRepo.saveAndFlush(entity);
				
				// Upload New Photos
				var images = storageService.saveAll(form.getFiles());
				entity.getImages().addAll(images);
			}
		});
		
		return form.getId();
	}

	private int create(PostForm form) {
		
		var files = form.getFiles().stream().filter(a -> !a.isEmpty()).toList();
		
		if(files.isEmpty()) {
			throw new AppBusinessException("Please select photos.");
		}
		
		var entity = new Post();
		entity.setTitle(form.getTitle());
		entity.setDescription(form.getDescription());
		entity.setPostAt(LocalDateTime.now());
		
		// Set Category
		var category = categoryRepo.findById(form.getCategoryId()).orElseThrow();
		entity.setCategory(category);
		
		// set owner
		var username = SecurityContextHolder.getContext().getAuthentication().getName();
		var member = memberRepo.findOneByEmail(username).orElseThrow();
		entity.setOwner(member);
		
		// Upload New Photos
		var images = storageService.saveAll(form.getFiles());
		entity.getImages().addAll(images);

		return postRepo.save(entity).getId();
	}

	@Transactional(readOnly = true)
	public PostForm findForEdit(int id) {
		return postRepo.findById(id).map(PostForm::from)
				.orElseThrow();
	}

}
