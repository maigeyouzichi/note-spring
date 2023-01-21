package com.spring.bean.factory.config;

/**
 * 记录类定义信息
 */
@SuppressWarnings("all")
public class BeanDefinition {

    /**
     * bean的定义变成Class,这样bean的实例化操作就会放在容器中进行处理了.
     */
    private Class beanClass;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }
}
