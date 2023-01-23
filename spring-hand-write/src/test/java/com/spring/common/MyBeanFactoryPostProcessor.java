package com.spring.common;

import com.spring.bean.BeansException;
import com.spring.bean.PropertyValue;
import com.spring.bean.PropertyValues;
import com.spring.bean.factory.ConfigurableListableBeanFactory;
import com.spring.bean.factory.config.BeanDefinition;
import com.spring.bean.factory.config.BeanFactoryPostProcessor;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("company", "改为：字节跳动"));
    }

}
