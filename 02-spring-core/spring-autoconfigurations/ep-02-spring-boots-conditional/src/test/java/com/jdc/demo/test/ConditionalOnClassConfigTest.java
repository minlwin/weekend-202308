package com.jdc.demo.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.demo.ConditionalOnClassConfig;
import com.jdc.demo.HelloBean;

@SpringJUnitConfig(classes = ConditionalOnClassConfig.class)
public class ConditionalOnClassConfigTest {
	
	@Autowired
	private ApplicationContext context;
	
	@Test
	void test() {
		
		var bean = context.getBean("helloBean");
		
		assertNotNull(bean);
		
		if(bean instanceof HelloBean hello) {
			System.out.println(hello.sayHello());
		}
	}
	
	@Test
	void test_missing_class() {
		var bean = context.getBean("missingClassBean");
		
		assertNotNull(bean);
		
		if(bean instanceof HelloBean hello) {
			System.out.println(hello.sayHello());
		}
	}

}
