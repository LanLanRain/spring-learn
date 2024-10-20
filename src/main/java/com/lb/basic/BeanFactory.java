package com.lb.basic;

import java.util.Properties;

public class BeanFactory {
   /*  public static UserService getUserService() {
        return new UserServiceImpl();
    } */

    /* public static UserService getUserService() {
        UserService userService = null;
        try {
            Class<?> aClass = Class.forName("com.lb.basic.UserServiceImpl");
            userService = (UserService) aClass.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return userService;
    } */

    private static Properties properties = new Properties();

    static {
        try {
            properties.load(BeanFactory.class.getClassLoader().getResourceAsStream("applicationContext.properties"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /* public static UserService getUserService() {
        UserService userService = null;
        try {
            Class<?> aClass = Class.forName(properties.getProperty("userService"));
            userService = (UserService) aClass.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return userService;
    }

    public static UserDao getUserDao() {
        UserDao userDao = null;
        try {
            Class<?> aClass = Class.forName(properties.getProperty("userDao"));
            userDao = (UserDao) aClass.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return userDao;
    } */

    public static Object getBean(String beanName) {
        Object bean = null;
        try {
            Class<?> aClass = Class.forName(properties.getProperty(beanName));
            bean = aClass.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return bean;
    }
}
