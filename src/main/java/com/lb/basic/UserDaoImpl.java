package com.lb.basic;

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
