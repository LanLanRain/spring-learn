package com.lb.basic;

public class UserServiceImpl implements UserService {

    // private UserDao userDao = new UserDaoImpl();

    // private UserDao userDao = BeanFactory.getUserDao();

    private UserDao userDao = (UserDao) BeanFactory.getBean("userDao");

    @Override
    public void register(User user) {
        userDao.save(user);
    }

    @Override
    public void login(String username, String password) {
        userDao.findByNameAndPassword(username, password);
    }
}
