package com.spring.bean;

import com.spring.bean.factory.config.BeanDefinition;
import com.spring.bean.factory.support.DefaultListableBeanFactory;
import org.junit.Test;

/**
 * @author lihao on 2023/1/17
 */
public class SpringIocTest {

    @Test
    public void test_BeanFactory(){
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2.注册 bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 3.第一次获取 bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();

        UserService userService2 = (UserService) beanFactory.getBean("userService");
        userService2.queryUserInfo();

        // 4.第二次获取 bean from Singleton
//        UserService userService_singleton = (UserService) beanFactory.getSingleton("userService");
//        userService_singleton.queryUserInfo();
    }

}
