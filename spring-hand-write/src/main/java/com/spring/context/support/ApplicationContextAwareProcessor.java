package com.spring.context.support;

import com.spring.bean.BeansException;
import com.spring.bean.factory.config.BeanPostProcessor;
import com.spring.context.ApplicationContext;
import com.spring.context.ApplicationContextAware;

/**
 * 包装处理类
 * 实现BeanPostProcessor,使其拥有BeanPostProcessor的能力,即:初始化前后对bean的处理
 * @author lihao
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ApplicationContextAware){
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

}