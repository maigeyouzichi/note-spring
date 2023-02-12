package com.spring.bean.factory;

import com.spring.bean.factory.aware.Aware;

/**
 * 实现此接口，既能感知到所属的 classLoader
 * @author lihao
 */
public interface BeanClassLoaderAware extends Aware {

    void setBeanClassLoader(ClassLoader classLoader);

}
