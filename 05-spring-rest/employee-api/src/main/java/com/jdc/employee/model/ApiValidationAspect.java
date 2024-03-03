package com.jdc.employee.model;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.BindingResult;

@Aspect
@Configuration
public class ApiValidationAspect {

	@Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
	public void apiMethods() {}
	
	@Before(value = "apiMethods() && args(..,result)", argNames = "result")
	public void handle(BindingResult result) {
		if(result.hasErrors()) {
			throw new ValidationException(
					result.getFieldErrors().stream().map(a -> a.getDefaultMessage()).toList());
		}
	}
}
