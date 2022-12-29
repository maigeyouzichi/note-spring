package com.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 加载bean第三种方式: 纯注解方式
 * @author lihao
 */
@Configuration
@ComponentScan("com.spring")
public class SpringConfig {
}