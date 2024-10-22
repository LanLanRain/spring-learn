package com.lb;

import com.lb.proxy.dynamic.User;
import com.lb.proxy.dynamic.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestProxy {
    @Test
    public void test1() {
        ApplicationContext applicationContext = new
                ClassPathXmlApplicationContext("applicationContext2.xml");
        UserService userService
                = (UserService) applicationContext.getBean("userService");

        userService.register(new User("张三", "123456"));
        userService.login("张三", "123456");
    }

    @Test
    public void test2() {
        ApplicationContext applicationContext = new
                ClassPathXmlApplicationContext("applicationContext2.xml");

    }

    @Test
    public void test3() {
        ApplicationContext applicationContext = new
                ClassPathXmlApplicationContext("applicationContext3.xml");
        com.lb.factory.UserService userService = (com.lb.factory.UserService) applicationContext.getBean("userService");

        userService.register(new com.lb.basic.User("张三", "123456"));
        userService.login("张三", "123456");

    }
}
