package com.spring.bean.factory;

/**
 * 实现此接口，既能感知到所属的 classLoader
 * @author lihao
 */
public interface BeanClassLoaderAware extends Aware {

    void setBeanClassLoader(ClassLoader classLoader);

}
