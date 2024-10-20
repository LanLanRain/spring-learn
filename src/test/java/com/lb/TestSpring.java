package com.lb;

import com.lb.basic.*;
import org.junit.Test;

public class TestSpring {

    @Test
    public void test1() {
        UserService userService = new UserServiceImpl();
        // UserService userService = new NewUserServiceImpl();

        userService.register(new User("张三", "123456"));
        userService.login("张三", "123456");
    }

    @Test
    public void test2() {
        // UserService userService = BeanFactory.getUserService();

        UserService userService = (UserService) BeanFactory.getBean("userService");

        userService.register(new User("张三", "123456"));
        userService.login("张三", "123456");
    }

    @Test
    public void test3() {
        Person person = (Person) BeanFactory.getBean("person");
        System.out.println("person = " + person);
    }
}
