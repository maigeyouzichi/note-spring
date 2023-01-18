package com.spring.bean.factory.support;

import com.spring.bean.BeansException;
import com.spring.bean.factory.config.BeanDefinition;
import java.lang.reflect.Constructor;

/**
 * 这里体现了类的各司其职,不关心的方法不去实现
 */
@SuppressWarnings("all")
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    //策略有两种实现,简单的是直接通过构造器直接实例化,cglib是生成代理对象,两种策略的区别
    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    /**
     * 只实现自己需要实现的抽象方法,和自己无关的方法不去实现,而是由其子类根据需要自行选择实现
     */
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean = null;
        try {
            bean = createBeanInstance(beanDefinition, beanName, args);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
        addSingleton(beanName, bean);
        return bean;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) {
        Constructor constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor ctor : declaredConstructors) {
            if (null != args && ctor.getParameterTypes().length == args.length) {
                constructorToUse = ctor;
                break;
            }
        }
        //获得构造器,生成cglib代理对象
        return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
