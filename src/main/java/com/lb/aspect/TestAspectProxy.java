package com.lb.aspect;

import com.lb.basic.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAspectProxy {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext3.xml");
        UserService userService = context.getBean("userService", UserService.class);
        userService.login("李四", "123456");

        userService.register(new User("张三", "123456"));
    }
}
