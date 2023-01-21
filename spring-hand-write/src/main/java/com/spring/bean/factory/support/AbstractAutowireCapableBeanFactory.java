package com.spring.bean.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.spring.bean.BeansException;
import com.spring.bean.PropertyValue;
import com.spring.bean.PropertyValues;
import com.spring.bean.factory.config.BeanDefinition;
import com.spring.bean.factory.config.BeanReference;
import java.lang.reflect.Constructor;

/**
 * 职责: 更细粒度的丰富了getBean方法
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
            // 给 Bean 填充属性
            applyPropertyValues(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
        addSingleton(beanName, bean);
        System.out.println("新创建bean: "+ bean.getClass().getName());
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

    /**
     * Bean 属性填充
     */
    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();
                if (value.getClass() == BeanReference.class) {
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }
                // 属性填充
                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception e) {
            throw new BeansException("Error setting property values：" + beanName);
        }
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
