package com.spring.bean.factory.config;

/**
 * 职责: 声明获取单例对象的能力
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);

    void registerSingleton(String beanName, Object singletonObject);

}
