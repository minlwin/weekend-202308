package com.jdc.weekend.controller;

import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.weekend.model.entity.Member.Role;

@Controller
@RequestMapping("/")
public class PublicHomeController {

	@GetMapping
	String index() {
		
		var authentication = Optional.ofNullable(SecurityContextHolder.getContext())
				.map(context -> context.getAuthentication());

		if(authentication.isPresent()) {
			var authorities = authentication.get().getAuthorities()
					.stream().map(a -> a.getAuthority()).toList();
			
			if(authorities.contains(Role.Admin.name())) {
				return "redirect:/admin/home";
			}
			
			if(authorities.contains(Role.Member.name())) {
				return "redirect:/member/home";
			}
		}
		
		return "redirect:/public/posts";
	}
}
