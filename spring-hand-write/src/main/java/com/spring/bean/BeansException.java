package com.spring.bean;

/**
 * 职责: 代表一种异常
 */
@SuppressWarnings("all")
public class BeansException extends RuntimeException {

    public BeansException(String msg) {
        super(msg);
    }

    public BeansException(String msg, Throwable cause) {
        super(msg, cause);
    }

}