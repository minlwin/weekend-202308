package com.jdc.demo;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jdc.demo.beans.HelloBeanCondition;

@Configuration
public class ConditionalOnClassConfig {

	@Bean
	@ConditionalOnClass(name = "com.jdc.demo.beans.HelloBeanCondition")
	HelloBean helloBean() {
		return new HelloBeanCondition();
	}
	
	@Bean
	@ConditionalOnMissingClass("com.jdc.demo.beans.NotOnClassBeanCondition")
	NotOnClassBean missingClassBean() {
		return new NotOnClassBean() {
			
			@Override
			public String sayHello() {
				return "Hello From Not On Class Bean";
			}
		};
	}
}
