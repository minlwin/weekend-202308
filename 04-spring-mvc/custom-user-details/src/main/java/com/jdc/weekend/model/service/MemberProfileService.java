package com.jdc.weekend.model.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.weekend.model.input.ProfileForm;
import com.jdc.weekend.model.output.MemberProfile;
import com.jdc.weekend.model.repo.MemberRepo;

@Service
@Transactional(readOnly = true)
public class MemberProfileService {

	@Autowired
	private MemberRepo repo;
	
	public Optional<MemberProfile> getProfile() {
		
		var authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication.isAuthenticated() && authentication.getAuthorities()
				.stream().map(a -> a.getAuthority()).toList().contains("Member")) {
			return repo.findOneByEmail(authentication.getName())
					.map(MemberProfile::from);
		}
		
		return Optional.empty();
	}
	
	@PreAuthorize("hasAuthority('Member')")
	public Optional<ProfileForm> getProfileForEdit() {
		var authentication = SecurityContextHolder.getContext().getAuthentication();
		return repo.findOneByEmail(authentication.getName())
				.map(ProfileForm::from);
	}

	@Transactional
	public void save(ProfileForm form) {
		repo.findById(form.getId()).ifPresent(entity -> {
			entity.setName(form.getName());
			entity.setEmail(form.getEmail());
			entity.setPhone(form.getPhone());
			entity.setGreeting(form.getGreeting());
		});
	}
}
