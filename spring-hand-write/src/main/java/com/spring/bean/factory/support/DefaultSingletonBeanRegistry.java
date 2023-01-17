package com.spring.bean.factory.support;

import com.spring.bean.factory.config.SingletonBeanRegistry;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("all")
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private final Map<String, Object> singletonObjects = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    protected void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }

}
