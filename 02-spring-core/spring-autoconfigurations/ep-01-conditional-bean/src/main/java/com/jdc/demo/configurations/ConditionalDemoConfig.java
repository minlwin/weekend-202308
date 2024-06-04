package com.jdc.demo.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import com.jdc.demo.beans.HelloBean;
import com.jdc.demo.conditions.MyFirstCondition;

@Configuration
public class ConditionalDemoConfig {

	@Bean
	@Conditional(MyFirstCondition.class)
	HelloBean helloBean() {
		return new HelloBean();
	}
}
