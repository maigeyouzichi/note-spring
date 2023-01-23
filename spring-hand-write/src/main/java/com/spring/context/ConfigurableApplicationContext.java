package com.spring.context;

import com.spring.bean.BeansException;

/**
 * 继承上下文容器,增加新的能力
 */
public interface ConfigurableApplicationContext extends ApplicationContext {

    /**
     * 刷新容器
     * @throws BeansException
     */
    void refresh() throws BeansException;

    void registerShutdownHook();

    void close();

}
