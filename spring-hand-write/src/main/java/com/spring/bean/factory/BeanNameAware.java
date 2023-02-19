package com.spring.bean.factory;

public interface BeanNameAware extends Aware {

    void setBeanName(String name);

}