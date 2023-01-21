package com.spring.bean.factory;

import com.spring.bean.BeansException;
import com.spring.bean.factory.config.AutowireCapableBeanFactory;
import com.spring.bean.factory.config.BeanDefinition;
import com.spring.bean.factory.config.ConfigurableBeanFactory;

/**
 * 提供分析和修改Bean以及预先实例化的操作接口
 */
@SuppressWarnings("all")
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

}