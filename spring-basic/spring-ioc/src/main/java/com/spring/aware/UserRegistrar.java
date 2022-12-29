package com.spring.aware;

import com.spring.entity.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * 有些场景下需要代码动态注入，以上方式都不适用。这时就需要创建 对象手动注入。
 * 实现BeanFactoryAware 配合 @Import(UserRegistrar.class) 可以将手动创建的Bean加载到IOC容器中
 * @author lihao
 */
public class UserRegistrar implements BeanFactoryAware {
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        DefaultListableBeanFactory listableBeanFactory = (DefaultListableBeanFactory)beanFactory;
        //方式一
//        BeanDefinition beanDefinition = new RootBeanDefinition(User.class);
//        listableBeanFactory.registerBeanDefinition(User.class.getName(),beanDefinition);
        //方式二
        User user = new User();
        //这样其实提供了很大的灵活性,可以处理一些事情再把Bean加载到IOC
        System.out.println("beanName: "+User.class.getName());
        listableBeanFactory.registerSingleton(User.class.getName(),user);
    }
}