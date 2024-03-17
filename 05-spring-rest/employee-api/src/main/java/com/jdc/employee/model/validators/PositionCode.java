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
@Constraint(validatedBy = PositionCodeConstraint.class)
public @interface PositionCode {

	String message() default "Invalid poistion code.";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
