package com.spring;

import com.spring.entity.Person;
import lombok.val;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ${Description}
 *
 * @author lihao on ${DATE}
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        val bean = context.getBean(Person.class);
        System.out.println("实例化完成,可以正式使用bean"+bean);
        ((ClassPathXmlApplicationContext)context).close();
    }
}