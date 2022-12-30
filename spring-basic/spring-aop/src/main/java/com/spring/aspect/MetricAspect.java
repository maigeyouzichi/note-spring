package com.spring.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author lihao
 */
@Aspect
@Component
@Slf4j
public class MetricAspect {

    // 定义切点 注解所在的方法 即是切点
    @Pointcut(value = "@annotation(com.spring.annotation.MsMetric)")
    public void pt() {
    }

    //定义通知
    @Around("pt()")
    public Object doLogging(ProceedingJoinPoint pjp) throws Throwable {
        try {
            log.info("----------------metric start--------------------");
            //计算方法执行的时间
            long startTime = System.currentTimeMillis();
            //方法调用
            Object ret = pjp.proceed();
            long endTime = System.currentTimeMillis();
            log.info("方法执行时间:{}ms", endTime - startTime);
            log.info("----------------metric end--------------------");
            return ret;
        } catch (Exception e) {
            log.error("异常信息", e);
            throw e;
        }
    }
}