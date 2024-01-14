package com.jdc.weekend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CustomUserDetailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomUserDetailsApplication.class, args);
	}

}
