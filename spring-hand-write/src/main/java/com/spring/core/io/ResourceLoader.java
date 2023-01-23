package com.spring.core.io;

/**
 * 按照资源加载的不同方式，资源加载器可以把这些方式集中到统一的类服务下进行处理，外部用户只需要传递资源地址即可，简化使用
 */
public interface ResourceLoader {

    /**
     * Pseudo URL prefix for loading from the class path: "classpath:"
     */
    String CLASSPATH_URL_PREFIX = "classpath:";

    /**
     * 传入location,自行判断资源加载方式,类似工厂,返回不同的Resource实现
     * 设计上屏蔽了很多内部细节
     */
    Resource getResource(String location);

}