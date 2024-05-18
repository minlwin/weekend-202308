package com.jdc.balance.model.service.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jdc.balance.model.exceptions.ApiTokenExpirationException;
import com.jdc.balance.model.exceptions.ApiTokenInvalidException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class JwtTokenFilter extends OncePerRequestFilter{
	
	@Autowired
	private JwtTokenProvider tokenProvider;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		try {
			var token = request.getHeader("Authorization");
			
			if(StringUtils.hasLength(token)) {
				var authentication = tokenProvider.parse(token);
				
				if(null != authentication) {
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			}
		} catch (ExpiredJwtException e) {
			throw new ApiTokenExpirationException();
		} catch (JwtException e) {
			throw new ApiTokenInvalidException(e.getMessage(), e);
		}
		
		filterChain.doFilter(request, response);
	}

}
