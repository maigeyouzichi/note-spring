package com.spring.aware;

import com.spring.entity.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * 有些场景下需要代码动态注入，以上方式都不适用。这时就需要创建 对象手动注入。
 * 实现BeanFactoryAware 配合 @Import(UserRegistrar.class) 可以将手动创建的Bean加载到IOC容器中(扫描的方式不可以加载至ioc)
 * 注: 这种方式相比于其他方式,UserRegistrar本身会加载到ioc容器中.
 * @author lihao
 */
public class UserRegistrar implements BeanFactoryAware {
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        DefaultListableBeanFactory listableBeanFactory = (DefaultListableBeanFactory)beanFactory;
        //方式一
//        BeanDefinition beanDefinition = new RootBeanDefinition(User.class);
//        listableBeanFactory.registerBeanDefinition(User.class.getName(),beanDefinition);
        //方式二,自己new出的单例对象,不会触发BeanPostProcessor方法的实现
        User user = new User();
        listableBeanFactory.registerSingleton(User.class.getName(),user);
    }
}