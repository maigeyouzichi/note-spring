package com.spring;

import com.spring.config.SpringConfig;
import com.spring.service.UserService;
import java.util.Arrays;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lihao
 */
public class Main {

    public static void main(String[] args) {
        //ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        //测试bean注入
        UserService userService = context.getBean("userService", UserService.class);
        userService.registerUser("ddd@mszlu.com","123456","ddd");
        System.out.println("------------");
        //测试bean别名
        String[] beanNames = context.getAliases("mailService");
        System.out.println("测试bean别名: "+Arrays.toString(beanNames));
        System.out.println("------------");
        //测试@configuration @bean beanName: 方法名 (数据库需要连接正确)
        System.out.println(context.getBean("dataSource"));
    }
}