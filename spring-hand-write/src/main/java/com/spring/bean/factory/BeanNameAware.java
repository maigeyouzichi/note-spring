package com.spring.bean.factory;

import com.spring.bean.factory.aware.Aware;

public interface BeanNameAware extends Aware {

    void setBeanName(String name);

}