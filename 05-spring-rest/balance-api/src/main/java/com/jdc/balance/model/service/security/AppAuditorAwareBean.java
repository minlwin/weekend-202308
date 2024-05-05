package com.jdc.balance.model.service.security;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AppAuditorAwareBean implements AuditorAware<String>{

	@Override
	public Optional<String> getCurrentAuditor() {
		return Optional.ofNullable(SecurityContextHolder.getContext())
				.map(context -> context.getAuthentication())
				.map(auth -> auth.getName()).or(() -> Optional.of("System"));
	}

}
