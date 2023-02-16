package com.spring.aop;

/**
 * 包含两个抽象方法,获取pointcut, 获取advice
 */
public interface PointcutAdvisor extends Advisor {

    /**
     * Get the Pointcut that drives this advisor.
     */
    Pointcut getPointcut();

}