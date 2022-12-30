package com.spring.validator.impl;

import com.spring.validator.Validator;
import org.springframework.stereotype.Component;

/**
 * @author lihao
 */
@Component
public class EmailValidator implements Validator {
    public void validate(String email, String password, String name) {
        if (!email.matches("^[a-z0-9]+\\@[a-z0-9]+\\.[a-z]{2,10}$")) {
            throw new IllegalArgumentException("invalid email: " + email);
        }
    }
}