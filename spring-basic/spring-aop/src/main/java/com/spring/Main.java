package com.spring;

import com.spring.config.SpringConfig;
import com.spring.service.MailService;
import com.spring.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * ${Description}
 *
 * @author lihao on ${DATE}
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserService bean = context.getBean(UserService.class);
        MailService mailService = context.getBean(MailService.class);
        System.out.println("用户: "+mailService.user);//如果被代理,直接从容器中获取也是一样,也是会被代理
        bean.registerUser("maigeyouzichi@gmail.com","123456","ddd");
    }
}