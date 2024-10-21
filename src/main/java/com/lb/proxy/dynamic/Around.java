package com.lb.proxy.dynamic;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class Around implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Object result = null;
        try {
            result = methodInvocation.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }
}
