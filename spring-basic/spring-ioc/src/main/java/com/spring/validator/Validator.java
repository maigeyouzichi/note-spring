package com.spring.validator;

/**
 * @author lihao
 */
public interface Validator {

    void validate(String email, String password, String name);
}