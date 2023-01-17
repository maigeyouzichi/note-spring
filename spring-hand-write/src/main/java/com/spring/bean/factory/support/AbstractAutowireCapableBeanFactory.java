package com.spring.bean.factory.support;

import com.spring.bean.BeansException;
import com.spring.bean.factory.config.BeanDefinition;

/**
 * 这里体现了类的各司其职,不关心的方法不去实现
 */
@SuppressWarnings("all")
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    /**
     * 只实现自己需要实现的抽象方法,和自己无关的方法不去实现,而是由其子类根据需要自行选择实现
     */
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        Object bean;
        try {
            bean = beanDefinition.getBeanClass().newInstance();//有参构造如何处理?
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
        //创建bean之后,加入到单例对象容器中
        addSingleton(beanName, bean);
        return bean;
    }

}
