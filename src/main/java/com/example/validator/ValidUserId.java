package com.example.validator;

import java.lang.annotation.*;

import jakarta.validation.*;

@Target({ ElementType.TYPE, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidUsernameValidator.class)
public @interface ValidUserId {
    String message() default "Invalid User Id";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
