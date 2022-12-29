package com.spring.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 不要忘记@Configuration ，@Bean注解生效的前提是JdbcConfig首先是受Spring容器管理的
 * 也就是说 Spring在扫描的时候，先扫描类，后扫描类中的方法
 * @author lihao
 */
@Configuration
public class JdbcConfig {

    @Bean
    public HikariDataSource dataSource(){
        HikariConfig configuration = new HikariConfig();
        configuration.setDriverClassName("com.mysql.cj.jdbc.Driver");
        configuration.setJdbcUrl("jdbc:mysql://101.133.163.204:3306/blacklake?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai&useLegacyDatetimeCode=false&allowMultiQueries=true");
        configuration.setUsername("root");
        configuration.setPassword("root");
        return new HikariDataSource(configuration);
    }
}