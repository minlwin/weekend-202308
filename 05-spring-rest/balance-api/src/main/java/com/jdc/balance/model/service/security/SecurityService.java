package com.jdc.balance.model.service.security;

import static com.jdc.balance.model.Commons.getOne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.balance.api.input.TokenRequest;
import com.jdc.balance.api.output.TokenResponse;
import com.jdc.balance.model.Status;
import com.jdc.balance.model.exceptions.ApiTokenInvalidException;
import com.jdc.balance.model.repo.AccountRepo;

@Service
@Transactional(readOnly = true)
public class SecurityService {
	
	private static final String ACCOUNT_DOMAIN = "Account";

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Autowired
	private AccountRepo accountRepo;

	public TokenResponse generate(TokenRequest request) {

		var authentication = authenticationManager.authenticate(request.authenticationToken());		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		var account = getOne(accountRepo.findOneByLoginId(request.loginId()), ACCOUNT_DOMAIN, request.loginId());
		var accessToken = tokenProvider.generateAccessToken(authentication);
		var refreshToken = tokenProvider.generateRefreshToken(authentication);
		
		var role = account.getRole().name();
		
		if(null != account.getEmployee() && account.getEmployee().getStatus() == Status.Applied) {
			role = account.getEmployee().getStatus().name();
		}
		
		return new TokenResponse(account.getName(), 
				account.getLoginId(), 
				role, accessToken, refreshToken);
	}

	public TokenResponse refresh(String token) {
		
		try {
			var authentication = tokenProvider.parse(token);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			var account = getOne(accountRepo.findOneByLoginId(authentication.getName()), ACCOUNT_DOMAIN, authentication.getName());
			var accessToken = tokenProvider.generateAccessToken(authentication);
			var refreshToken = tokenProvider.generateRefreshToken(authentication);
			
			var role = account.getRole().name();
			
			if(null != account.getEmployee() && account.getEmployee().getStatus() == Status.Applied) {
				role = account.getEmployee().getStatus().name();
			}

			return new TokenResponse(account.getName(), 
					account.getLoginId(), 
					role, accessToken, refreshToken);
			
		} catch (Exception e) {
			throw new ApiTokenInvalidException("Refresh token expiration.", e);
		}

	}

}
