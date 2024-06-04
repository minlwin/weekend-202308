package com.jdc.demo.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.demo.beans.HelloBean;
import com.jdc.demo.configurations.ConditionalDemoConfig;

@SpringJUnitConfig(classes = ConditionalDemoConfig.class)
@TestPropertySource("/test.properties")
public class MyFirstConditionTest {

	@Autowired
	private ApplicationContext context;
	
	@Test
	void test_load() {
		var bean = context.getBean(HelloBean.class);
		assertNotNull(bean);
		
		System.out.println(bean.sayHello());
	}
}
