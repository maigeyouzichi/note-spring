package com.spring.bean.factory;

/**
 * 声明要丢弃的接口
 */
public interface DisposableBean {

    void destroy() throws Exception;

}