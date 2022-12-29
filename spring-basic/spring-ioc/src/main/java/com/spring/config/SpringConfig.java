package com.spring.config;

import com.spring.entity.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 加载bean第三种方式: 纯注解方式
 * @author lihao
 */
@Configuration
//@ComponentScan("com.spring")
@Import(User.class)//User本身没有被任何注解标记,通过@Import注解可以将其加载到ioc容器中
public class SpringConfig {
}