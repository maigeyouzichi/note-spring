package com.spring.validator;

import com.spring.entity.User;
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

    //标记了require=false,如果容器能获取到bean,就注入,如果容器中获取不到bean,会执行默认逻辑,如果没有默认逻辑,注入null
    @Autowired(required = false)
    private User user = new User(100L, "defaultName", "maigeyouzichi@gmail.com", "password");

    public void validate(String email, String password, String name) {
        System.out.println("注入bean数量: "+ validators.size());
        for (var validator : this.validators) {
            System.out.println("当前实现Bean: "+ validator.hashCode());
            validator.validate(email, password, name);
        }
    }

    public void showUser() {
        System.out.println("查看user: "+ user);
    }
}