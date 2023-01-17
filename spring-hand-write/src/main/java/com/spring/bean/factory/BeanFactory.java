package com.spring.bean.factory;

import com.spring.bean.BeansException;

@SuppressWarnings("all")
public interface BeanFactory {

    Object getBean(String name) throws BeansException;

}
