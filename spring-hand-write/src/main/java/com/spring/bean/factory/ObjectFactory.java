package com.spring.bean.factory;

import com.spring.bean.BeansException;

public interface ObjectFactory<T> {

    T getObject() throws BeansException;

}