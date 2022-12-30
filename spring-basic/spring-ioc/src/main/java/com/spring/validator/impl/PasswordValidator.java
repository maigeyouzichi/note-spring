package com.spring.validator.impl;

import com.spring.validator.Validator;
import org.springframework.stereotype.Component;

/**
 * @author lihao
 */
@Component
public class PasswordValidator implements Validator {
    public void validate(String email, String password, String name) {
        if (!password.matches("^.{6,20}$")) {
            throw new IllegalArgumentException("invalid password");
        }
    }
}