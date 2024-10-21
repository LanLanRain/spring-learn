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
}
