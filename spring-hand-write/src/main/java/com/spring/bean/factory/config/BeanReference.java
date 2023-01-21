package com.spring.bean.factory.config;

/**
 * 职责: 指向一个对象,编译期间对象不存在,通过这个类在编译器声明,在运行期间从容器中获取单例bean
 */
@SuppressWarnings("all")
public class BeanReference {

    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }

}