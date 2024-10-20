package com.lb;

import com.lb.basic.*;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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

    @Test
    public void test4() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Person person = applicationContext.getBean("person", Person.class);
        System.out.println("person = " + person);
    }

    @Test
    public void test5() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Person person = applicationContext.getBean(Person.class);
        System.out.println("person = " + person);
    }

    @Test
    public void test6() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();

        /*
         * beanDefinitionName = person
         * beanDefinitionName = person1
         */
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println("beanDefinitionName = " + beanDefinitionName);
        }

        String[] beanNamesForType = applicationContext.getBeanNamesForType(Person.class);

        /*
         * beanName = person
         * beanName = person1
         */
        for (String beanName : beanNamesForType) {
            System.out.println("beanName = " + beanName);
        }

        boolean person = applicationContext.containsBean("person");
        boolean person1 = applicationContext.containsBean("person1");

        System.out.println("person = " + person);
        System.out.println("person1 = " + person1);

        applicationContext.containsBeanDefinition("person");
        applicationContext.containsBeanDefinition("person1");
    }




}
