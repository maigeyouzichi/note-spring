package com.spring.context;

import com.spring.bean.BeansException;
import com.spring.bean.factory.aware.Aware;

/**
 * 实现此接口，既能感知到所属的 ApplicationContext
 * @author lihao
 */
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;

}