package com.spring.bean.factory.config;

import com.spring.bean.factory.HierarchicalBeanFactory;

/**
 * 可获取 BeanPostProcessor、BeanClassLoader等的一个配置化接口
 */
@SuppressWarnings("all")
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

}