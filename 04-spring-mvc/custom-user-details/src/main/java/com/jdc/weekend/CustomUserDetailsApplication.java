package com.jdc.weekend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.jdc.weekend.model.BaseRepositoryImpl;

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
public class CustomUserDetailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomUserDetailsApplication.class, args);
	}

}
