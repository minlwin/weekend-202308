package com.jdc.demo.beans;

import com.jdc.demo.HelloBean;

public class HelloBeanCondition implements HelloBean{

	@Override
	public String sayHello() {
		return "Hello from conditional on class";
	}
}
