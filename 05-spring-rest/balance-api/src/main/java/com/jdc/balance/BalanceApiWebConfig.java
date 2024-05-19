package com.jdc.balance;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.jdc.balance.model.BaseRepoImpl;
import com.jdc.balance.model.service.security.AdminUserInitializer;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(repositoryBaseClass = BaseRepoImpl.class)
public class BalanceApiWebConfig implements WebMvcConfigurer{
	
	@Value("${spring.mvc.format.date}")
	private String dateFormat;
	@Value("${spring.mvc.format.date-time}")
	private String dateTimeFormat;

	@Autowired
	private AdminUserInitializer adminUserInitializer;
	
	@Bean
	ApplicationRunner applicationRunner() {
		return args -> adminUserInitializer.initilizeAdmin();
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins("*")
			.allowedMethods("*")
			.allowedHeaders("*");
	}
	
	@Bean
	Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
		return builder -> {
			builder.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(dateFormat)));
			builder.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimeFormat)));
			builder.deserializers(new LocalDateDeserializer(DateTimeFormatter.ofPattern(dateFormat)));
			builder.deserializers(new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(dateFormat)));
		};
	}
}
