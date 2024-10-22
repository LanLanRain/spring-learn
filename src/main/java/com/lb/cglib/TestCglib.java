package com.lb.cglib;

import com.lb.proxy.dynamic.User;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class TestCglib {
    public static void main(String[] args) {
        // 创建一个 UserService 接口的实现类 UserServiceImpl 的实例
        UserService userService = new UserService();

        // 创建一个 Enhancer 对象，用于生成代理类
        Enhancer enhancer = new Enhancer();

        // 设置 Enhancer 对象的类加载器
        enhancer.setClassLoader(TestCglib.class.getClassLoader());

        // 设置 Enhancer 对象的父类，即要代理的目标类
        enhancer.setSuperclass(UserService.class);

        // 创建一个 MethodInterceptor 接口的实现类，用于处理代理对象的方法调用
        MethodInterceptor interceptor = new MethodInterceptor() {
            /**
             * 当代理对象的方法被调用时，会自动调用这个 intercept 方法。
             *
             * @param obj 代理对象。
             * @param method 被调用的方法。
             * @param args 方法的参数。
             * @param proxy 方法的代理对象。
             * @return 方法的返回值。
             * @throws Throwable 任何可能抛出的异常。
             */
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                // 在执行目标方法之前，输出一些信息
                System.out.println("执行目标方法之前");
                // 执行目标方法
                Object ret = method.invoke(userService, args);
                // 在执行目标方法之后，输出一些信息
                System.out.println("执行目标方法之后");
                // 返回目标方法的执行结果
                return ret;
            }
        };

        // 设置 Enhancer 对象的回调函数，即 MethodInterceptor 接口的实现类
        enhancer.setCallback(interceptor);

        // 使用 Enhancer 对象创建一个代理对象
        UserService newedProxyInstance = (UserService) enhancer.create();
        newedProxyInstance.register(new User("张三", "123456"));
        newedProxyInstance.login("张三", "123456");
    }
}
