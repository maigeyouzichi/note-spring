package com.spring.bean.factory.config;

@SuppressWarnings("all")
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);

}
