package com.jdc.balance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.jdc.balance.model.BaseRepoImpl;
import com.jdc.balance.model.service.security.AdminUserInitializer;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(repositoryBaseClass = BaseRepoImpl.class)
public class BalanceApiWebConfig implements WebMvcConfigurer{

	@Autowired
	private AdminUserInitializer adminUserInitializer;
	
	@Bean
	ApplicationRunner applicationRunner() {
		return args -> adminUserInitializer.initilizeAdmin();
	}
}
