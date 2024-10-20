package com.lb;

import com.lb.basic.*;
import com.lb.basic.constructor.Customer;
import com.lb.factorybean.ConnectionFactoryBean;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;

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

    @Test
    public void test7() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Person person = applicationContext.getBean("person", Person.class);
        person.setId(1);
        person.setName("张三");
        System.out.println("person = " + person);
    }

    @Test
    public void test8() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Person person = applicationContext.getBean("person2", Person.class);
        System.out.println("person = " + person);
    }

    @Test
    public void test9() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Customer customer = applicationContext.getBean("customer", Customer.class);
        System.out.println("customer = " + customer);
    }

    @Test
    public void test10() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Connection conn = applicationContext.getBean("connection", Connection.class);
        System.out.println("conn = " + conn);
    }

    @Test
    public void test11() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        ConnectionFactoryBean connectionFactoryBean = applicationContext.getBean("&connection", ConnectionFactoryBean.class);
        System.out.println("connectionFactoryBean = " + connectionFactoryBean);
    }

    @Test
    public void test12() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Connection conn = (Connection) applicationContext.getBean("conn");
        System.out.println("conn = " + conn);
    }

}
