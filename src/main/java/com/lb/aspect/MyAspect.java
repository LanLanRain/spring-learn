package com.lb.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 定义一个切面类，用于在方法执行前后添加额外的逻辑
 */
@Aspect
public class MyAspect {

    @Pointcut("execution(* login(..))")
    public void myPointcut(){}

    @Around("execution(* register(..))")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        try {
            System.out.println("register before");
            // 执行目标方法
            Object proceed = proceedingJoinPoint.proceed();
            System.out.println("register after");
            return proceed;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Around(value = "myPointcut()")
    public Object around1(ProceedingJoinPoint proceedingJoinPoint) {
        try {
            System.out.println("login before");
            // 执行目标方法
            Object proceed = proceedingJoinPoint.proceed();
            System.out.println("login after");
            return proceed;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
