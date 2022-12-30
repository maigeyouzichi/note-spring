package com.spring.config;

import org.springframework.context.annotation.Configuration;

/**
 * 加载bean第三种方式: 纯注解方式
 * @author lihao
 */
@Configuration
//@ComponentScan("com.spring") //这种需要被扫描的类被@compent注解修饰
//@Import(User.class)//User本身没有被任何注解标记,通过@Import注解可以将其加载到ioc容器中
//@Import(MyImportSelector.class)
//@Import(MyImportBeanDefinitionRegistrar.class)
//@Import(UserRegistrar.class)
public class SpringConfig {
}