package com.spring.bean.factory.support;

import com.spring.bean.BeansException;
import com.spring.bean.factory.BeanFactory;
import com.spring.bean.factory.config.BeanDefinition;

/**
 * 抽象类定义模板方法
 * 继承自 DefaultSingletonBeanRegistry 拥有了注册和获取单例Bean的能力
 */
@SuppressWarnings("all")
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String name) throws BeansException {
        //优先去获取单例bean
        Object bean = getSingleton(name);
        if (bean != null) {
            System.out.println("单例容器中存在目标对象,直接返回");
            return bean;
        }
        System.out.println("单例容器中不存在目标对象,需要临时创建");
        //否则,去创建对象并返回,调用方法为抽象方法,其具体逻辑依赖于其实现类,对获取bean的逻辑做了规定,但是没有完全规定
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name, beanDefinition);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;

}
