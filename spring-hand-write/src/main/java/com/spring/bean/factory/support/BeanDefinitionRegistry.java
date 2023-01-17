package com.spring.bean.factory.support;

import com.spring.bean.factory.config.BeanDefinition;

@SuppressWarnings("all")
public interface BeanDefinitionRegistry {

    /**
     * 向注册表中注册 BeanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

}