package com.spring.validator;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 测试List方式注入
 * @author lihao
 */
@Component
public class Validators {
    @Autowired
    private List<Validator> validators;

    public void validate(String email, String password, String name) {
        System.out.println("注入bean数量: "+ validators.size());
        for (var validator : this.validators) {
            System.out.println("当前实现Bean: "+ validator.hashCode());
            validator.validate(email, password, name);
        }
    }
}