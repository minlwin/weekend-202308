package com.jdc.employee.model.validators;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RUNTIME)
@Target({ FIELD, PARAMETER })
@Constraint(validatedBy = DepartmentCodeConstraint.class)
public @interface DepartmentCode {

	String message() default "Invalid Department Code";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
