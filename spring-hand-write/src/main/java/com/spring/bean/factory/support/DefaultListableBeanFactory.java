package com.spring.bean.factory.support;

import com.spring.bean.BeansException;
import com.spring.bean.factory.config.BeanDefinition;
import java.util.HashMap;
import java.util.Map;

/**
 * DefaultListableBeanFactory是源码中比较核心的类
 * 职责: 最终获取了getBean的能力,自身或其父类实现了全部的细节定义
 */
@SuppressWarnings("all")
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {

    private final Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) throw new BeansException("No bean named '" + beanName + "' is defined");
        return beanDefinition;
    }

}
