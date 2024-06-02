package com.jdc.students.auth;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClient;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jdc.students.auth.model.AccessUserInfo;
import com.jdc.students.exceptions.AppTokenExpirationException;
import com.jdc.students.exceptions.AppTokenInvalidationException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class AccessTokenFilter extends OncePerRequestFilter{
	
	@Value("${app.auth.api.url}")
	private String authUrl;
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		var token = request.getHeader("Authorization");
		
		if(StringUtils.hasLength(token)) {
			
			MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
			form.add("token", token);
			
			var result = RestClient.create(authUrl)
					.post().uri("token/validate")
					.body(form)
					.accept(MediaType.APPLICATION_JSON)
					.retrieve()
					.onStatus(this::filterStatus, this::handleError)
					.body(AccessUserInfo.class);
			
			var authentication = UsernamePasswordAuthenticationToken.authenticated(result.code(), null, result.authorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		filterChain.doFilter(request, response);
	}
	
	private boolean filterStatus(HttpStatusCode status) {
		return status.is4xxClientError() || status.is5xxServerError();
	}
	
	private void handleError(HttpRequest request, ClientHttpResponse response) {
		try {
			var exception = switch(response.getStatusCode().value()) {
			case 401 -> new AppTokenInvalidationException("Invalid token information.");
			case 408 -> new AppTokenExpirationException();
			default -> null;
			};
			
			if(null != exception) {
				throw exception;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
