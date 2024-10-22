package com.lb.factory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyBeanPostProcessor implements BeanPostProcessor {
    /**
     * 在 bean 初始化之前执行的处理方法。
     * 该方法在当前实现中直接返回传入的 bean，不做任何处理。
     *
     * @param bean     被处理的 bean 对象。
     * @param beanName bean 的名称。
     * @return 返回传入的 bean 对象。
     * @throws BeansException 如果在处理过程中发生异常。
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    /**
     * 在 bean 初始化之后执行的处理方法。
     * 该方法使用 JDK 动态代理为传入的 bean 创建一个代理对象，并在代理对象的方法调用前后输出一些信息。
     *
     * @param bean     被处理的 bean 对象。
     * @param beanName bean 的名称。
     * @return 返回创建的代理对象。
     * @throws BeansException 如果在处理过程中发生异常。
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // 创建一个 InvocationHandler 接口的实现类，用于处理代理对象的方法调用
        InvocationHandler invocationHandler = new InvocationHandler() {
            /**
             * 当代理对象的方法被调用时，会自动调用这个 invoke 方法。
             * 在这个方法中，我们在执行目标方法之前输出 "before"，执行目标方法之后输出 "after"。
             *
             * @param proxy 代理对象。
             * @param method 被调用的方法。
             * @param args 方法的参数。
             * @return 方法的返回值。
             * @throws Throwable 任何可能抛出的异常。
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("before");
                // 执行目标方法
                Object invoke = method.invoke(bean, args);
                System.out.println("after");
                // 返回目标方法的执行结果
                return invoke;
            }
        };
        // 使用 Proxy 类的 newProxyInstance 方法创建一个代理对象
        return Proxy.newProxyInstance(ProxyBeanPostProcessor.class.getClassLoader(), bean.getClass().getInterfaces(), invocationHandler);
    }
}
