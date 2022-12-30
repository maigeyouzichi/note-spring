package com.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 配置类
 * @author lihao
 */
@Configuration
//这种需要被扫描的类被@compent注解修饰
@ComponentScan("com.spring")
//注意开启AOP的支持，并且代理设置为cglib动态代理，因为UserService没有接口
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class SpringConfig {
}