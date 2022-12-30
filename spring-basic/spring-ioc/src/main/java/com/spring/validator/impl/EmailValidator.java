package com.spring.validator.impl;

import com.spring.validator.Validator;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author lihao
 * @Order()的顺序指定注入到List中的顺序,因为List是有序的
 */
@Component
@Order(1)
public class EmailValidator implements Validator {
    public void validate(String email, String password, String name) {
        if (!email.matches("^[a-z0-9]+\\@[a-z0-9]+\\.[a-z]{2,10}$")) {
            throw new IllegalArgumentException("invalid email: " + email);
        }
    }
}