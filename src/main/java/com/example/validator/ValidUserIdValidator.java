package com.example.validator;

import com.example.demo.entity.User;

import jakarta.validation.*;

public class ValidUserIdValidator implements ConstraintValidator<ValidUserId, User> {

    @Override
    public boolean isValid(User user, ConstraintValidatorContext context) {
        if (user == null) {
            return true;
        }
        return user.getId() != null;
    }
}