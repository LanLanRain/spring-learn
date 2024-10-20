# spring

## 1. 工厂设计模式

```java
public interface UserService {
    void register(User user);

    void login(String username, String password);
}

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public void register(User user) {
        userDao.save(user);
    }

    @Override
    public void login(String username, String password) {
        userDao.findByNameAndPassword(username, password);
    }
}
```

```java
public interface UserDao {
    void save(User user);

    void findByNameAndPassword(String name, String password);
}

public class UserDaoImpl implements UserDao {
    @Override
    public void save(User user) {
        System.out.println("insert into user = " + user);
    }

    @Override
    public void findByNameAndPassword(String name, String password) {
        System.out.println("select * from user where name = " + name + " and password = " + password);
    }
}
```

```java
public class User {
    private String name;
    private String password;

  	//...
}
```

```java
@Test
    public void test1() {
        UserService userService = new UserServiceImpl();
        // UserService userService = new NewUserServiceImpl();

        userService.register(new User("张三", "123456"));
        userService.login("张三", "123456");
    }
```

在这一版本代码中，测试类中`UserService userService = new UserServiceImpl();`存在代码耦合；在`private UserDao userDao = new UserDaoImpl();`存在代码耦合。



采用工厂类解决耦合：

```java
public static UserService getUserService() {
    return new UserServiceImpl();
} 
```

```java
@Test
public void test2() {
    // UserService userService = BeanFactory.getUserService();

    UserService userService = (UserService) BeanFactory.getBean("userService");

    userService.register(new User("张三", "123456"));
    userService.login("张三", "123456");
}
```

这样就解决了测试类中的耦合。但是工厂中又存在耦合了。



在resource根目录下新建`applicationContext.properties`文件，在工厂中读取配置文件，解决工厂中的字符串耦合。

```properties
userService=com.lb.basic.UserServiceImpl
userDao=com.lb.basic.UserDaoImpl
```

```java
private static Properties properties = new Properties();

    static {
        try {
     properties.load(BeanFactory.class.getClassLoader().getResourceAsStream("applicationContext.properties"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

public static UserService getUserService() {
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
} 
```

这样又发现重复代码太多，可以设计一个通用工厂：

```java
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
```

只是在使用时需要强转。



## 2. Spring核心API

### 2.1 ApplicationContext接口

```properties
ClassPathXmlApplicationContext   非web环境(main junit)

XmlWebApplicationContext	web环境
```
![](img/QQ20241020-161452.png)

`ApplicationContext`是一个重量级资源，不会重复创建。一个应用只会创建一个工厂对象，并且它一定是线程安全的。



### 2.2 程序开发

1. 创建类型
2. 配置文件
3. 通过工厂类获取对象

```java
public class Person {
}

<bean id="person" class="com.lb.basic.Person"/>

@Test
public void test4() {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
    Person person = applicationContext.getBean("person", Person.class);
    System.out.println("person = " + person);
}
```

### 2.3 Spring 工厂实现原理

![](/home/lb/桌面/IdeaProjects/spring-learn/img/QQ20241020-165851.png)

## 3. 注入

什么是注入？通过Spring工厂及配置文件，为所创建的成员变量赋值。

为什么要注入？通过编码的方式为成员变量赋值，存在代码耦合。
![](img/QQ20241020-203153.png)

### 3.1 如何进行注入？

1. 类的成员变量提供set get方法。

2. 配置Spring的配置文件。

   ```java
   <bean id="person2" name="p" class="com.lb.basic.Person">
       <property name="name" value="lb"/>
       <property name="id" value="18"/>
   </bean>
   ```

3. 注入的好处：解耦合。



### 3.2 注入的简易原理

![](img/QQ20241020-204002.png)

Spring通过底层调用对象属性的set方法，完成对成员变量赋值。这种方式也称为set注入。



### 3.3 Set注入详解

![](img/QQ20241020-204728.png)

### 3.4 构造注入

![](img/QQ20241020-220346.png)