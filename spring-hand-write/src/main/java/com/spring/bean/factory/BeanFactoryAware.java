package com.spring.bean.factory;

import com.spring.bean.BeansException;

/**
 * 1,Interface to be implemented by beans that wish to be aware of their owning {@link BeanFactory}.
 * 2,实现此接口，既能感知到所属的 BeanFactory
 * @author lihao on 2023/2/12
 */
public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
