package com.spring.aspect;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 定义切面类
 * @author lihao
 */
//@Aspect
//@Component
@Slf4j
public class LogAspect {

    // 定义切点 在执行UserService的每个方法前执行:
    @Pointcut("execution(public * com.spring.service.UserService.*(..))")
    public void pt() {}

    //前置通知
    //@Before("pt()")
    public void before() {
        System.out.println("before advice ...");
    }

    //后置通知
    //@After("pt()")
    public void after() {
        System.out.println("after advice ...");
    }

    //环绕通知
    @Around("pt()")
    public void around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("around before advice ...");
        Object ret = pjp.proceed();
        System.out.println("around after advice ...");
    }

    //定义通知
    //@Around("pt()")
    public Object doLogging(ProceedingJoinPoint pjp) throws Throwable{
        try {
            log.info("----------------log start--------------------");
            //1. 打印执行的类和方法
            Signature signature = pjp.getSignature();
            String methodName = signature.getName();
            String interfaceName = signature.getDeclaringTypeName();
            log.info("接口名称:{}",interfaceName);
            log.info("方法名称:{}",methodName);
            //2. 打印执行方法的参数
            log.info("方法参数:{}", JSON.toJSONString(pjp.getArgs()));
            //3. 计算方法执行的时间
            long startTime = System.currentTimeMillis();
            //方法调用
            Object ret = pjp.proceed();
            long endTime = System.currentTimeMillis();
            log.info("方法执行时间:{}ms", endTime-startTime);
            log.info("----------------log end--------------------");
            return ret;
        }catch (Exception e){
            //4. 如果有异常，记录异常
            log.error("异常信息",e);
            throw e;
        }
    }
}