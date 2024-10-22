package com.lb.jdk;

import com.lb.proxy.dynamic.User;
import com.lb.proxy.dynamic.UserService;
import com.lb.proxy.dynamic.UserServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TestJdkProxy {
    public static void main(String[] args) {
        // 创建一个 UserService 接口的实现类 UserServiceImpl 的实例
        UserService userService = new UserServiceImpl();

        // 创建一个 InvocationHandler 接口的实现类，用于处理代理对象的方法调用
        InvocationHandler handler = new InvocationHandler() {
            /**
             * 当代理对象的方法被调用时，会自动调用这个 invoke 方法。
             *
             * @param proxy 代理对象。
             * @param method 被调用的方法。
             * @param args 方法的参数。
             * @return 方法的返回值。
             * @throws Throwable 任何可能抛出的异常。
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 在执行目标方法之前，输出一些信息
                System.out.println("执行目标方法之前");
                // 执行目标方法
                Object ret = method.invoke(userService, args);
                // 返回目标方法的执行结果
                return ret;
            }
        };

        // 使用 Proxy 类的 newProxyInstance 方法创建一个代理对象
        UserService newedProxyInstance = (UserService) Proxy.newProxyInstance(TestJdkProxy.class.getClassLoader(), userService.getClass().getInterfaces(), handler);

        // 调用代理对象的方法
        newedProxyInstance.register(new User("张三", "123456"));
        newedProxyInstance.login("张三", "123456");
    }
}
