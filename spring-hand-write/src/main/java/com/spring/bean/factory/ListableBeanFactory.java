package com.spring.bean.factory;

import com.spring.bean.BeansException;
import java.util.Map;

/**
 * 实现BeanFactory,增强能力
 */
public interface ListableBeanFactory extends BeanFactory{

    /**
     * 按照类型返回 Bean 实例
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * Return the names of all beans defined in this registry.
     * 返回注册表中所有的Bean名称
     */
    String[] getBeanDefinitionNames();

}