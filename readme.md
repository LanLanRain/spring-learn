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
