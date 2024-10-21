package com.lb.proxy.dynamic;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class Before implements MethodBeforeAdvice {

    /**
     * 在目标方法执行前执行的增强逻辑
     *
     * @param method 目标方法       boolean login(String name, String password)
     * @param args   目标方法的参数  String username, String password
     * @param target 目标对象       UserServiceImpl
     * @throws Throwable 如果在执行通知时发生异常
     */
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("---Before.before Log---");
    }
}
