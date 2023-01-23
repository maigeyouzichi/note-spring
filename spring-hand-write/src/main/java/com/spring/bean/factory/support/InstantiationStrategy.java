package com.spring.bean.factory.support;

import com.spring.bean.BeansException;
import com.spring.bean.factory.config.BeanDefinition;
import java.lang.reflect.Constructor;

/**
 * 职责: 声明实例化bean策略接口
 */
public interface InstantiationStrategy {

    /**
     *
     * @param beanDefinition
     * @param beanName
     * @param ctor  java.lang.reflect 包下的 Constructor 类
     * @param args
     * @return
     * @throws BeansException
     */
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException;

}
