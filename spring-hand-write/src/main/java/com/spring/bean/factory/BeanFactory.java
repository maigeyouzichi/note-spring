package com.spring.bean.factory;

import com.spring.bean.BeansException;

/**
 * 职责: 声明获取bean的能力
 */
public interface BeanFactory {

    Object getBean(String name) throws BeansException;

    Object getBean(String name, Object... args) throws BeansException;

    <T> T getBean(String name, Class<T> requiredType) throws BeansException;

}
