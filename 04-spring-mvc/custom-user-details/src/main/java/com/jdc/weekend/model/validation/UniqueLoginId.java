package com.jdc.weekend.model.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RUNTIME)
@Target({ FIELD, PARAMETER })
@Constraint(validatedBy = UniqueLoginIdConstraint.class)
public @interface UniqueLoginId {

	String message() default "Your email has been already used.";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
