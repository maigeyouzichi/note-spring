package com.spring.registrar;

import com.spring.entity.User;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 实现ImportBeanDefinitionRegistrar配合@Import(MyImportBeanDefinitionRegistrar.class),依然可以把没有注解修饰的User类注入到IOC容器
 * @author lihao
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {

        BeanDefinition beanDefinition =  new RootBeanDefinition(User.class.getName());
        beanDefinitionRegistry.registerBeanDefinition(User.class.getName(),beanDefinition);
    }
}