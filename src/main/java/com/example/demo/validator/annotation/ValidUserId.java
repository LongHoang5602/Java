package com.example.demo.validator.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.example.demo.validator.ValidUserIdValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ TYPE, FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = ValidUserIdValidator.class)
@Documented
public @interface ValidUserId {
    String message() default "Invalid User Id";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
