package com.spring.aop.aspectj;

import com.spring.aop.Pointcut;
import com.spring.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;

/**
 * pointcut 和 advice 的组合, 可以获取pointcut, advice决定在pointcut执行什么操作
 * 或者说: 该类把切面pointcut,拦截方法advice,拦截表达式包装在一起,这样就可以在xml中配置一个PointcutAdvisor了
 * @author lihao
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    // 切面
    private AspectJExpressionPointcut pointcut;
    // 具体的拦截方法
    private Advice advice;
    // 表达式
    private String expression;

    public void setExpression(String expression){
        this.expression = expression;
    }

    @Override
    public Pointcut getPointcut() {
        if (null == pointcut) {
            pointcut = new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    public void setAdvice(Advice advice){
        this.advice = advice;
    }

}