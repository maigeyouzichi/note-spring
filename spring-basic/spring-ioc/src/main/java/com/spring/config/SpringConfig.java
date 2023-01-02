package com.spring.config;

import com.spring.entity.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 加载bean第三种方式: 纯注解方式
 * @author lihao
 */
@Configuration
//@ComponentScan("com.spring") //这种需要被扫描的类被@compent注解修饰
@Import(User.class)//User本身没有被任何注解标记,扫描扫到(或指定)@Import注解,通过@Import注解可以将其加载到ioc容器中
//个人理解@Import注解的作用,就是让容器去处理没有被@Component注解修饰的类
//@Import(MyImportSelector.class)
//@Import(MyImportBeanDefinitionRegistrar.class)
//@Import(UserRegistrar.class)
public class SpringConfig {
}