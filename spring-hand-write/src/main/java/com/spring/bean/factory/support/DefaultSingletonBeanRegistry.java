package com.spring.bean.factory.support;

import com.spring.bean.factory.config.SingletonBeanRegistry;
import java.util.HashMap;
import java.util.Map;

/**
 * 单例注册类
 */
@SuppressWarnings("all")
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private final Map<String, Object> singletonObjects = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    /**
     * 访问级别是子类可用,保证每个继承它的子类都拥有缓存对象的能力
     */
    protected void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }

}
