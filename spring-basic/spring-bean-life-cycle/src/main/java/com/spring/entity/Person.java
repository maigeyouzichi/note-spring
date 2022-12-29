package com.spring.entity;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author lihao
 */
public class Person implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean, DisposableBean {

    private String name;

    public Person() {
        System.out.println("第一步: Person类构造方法,进行bean实例化");
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        System.out.println("第二步: set方法被调用,注入bean所需要的属性");
    }

    public void setBeanName(String beanName) {
        System.out.println("第三步: setBeanName被调用,获得beanName,beanName:" + beanName);
    }

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("第四步: setBeanFactory被调用,获得自己所在的BeanFactory,beanFactory: "+beanFactory.hashCode());
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("第五步: setApplicationContext被调用,获取自身所在的ApplicationContext: "+ applicationContext.hashCode());
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("第七步: afterPropertiesSet被调用");
    }

    //自定义的初始化函数
    public void myInit() {
        System.out.println("第八步: 自定义的myInit被调用");
    }

    public void destroy() throws Exception {
        System.out.println("第十步: destory被调用");
    }

    //自定义的销毁方法
    public void myDestroy() {
        System.out.println("第十一步: 自定义的myDestroy被调用");
    }

    public String toString() {
        return "name is :" + name;
    }
}