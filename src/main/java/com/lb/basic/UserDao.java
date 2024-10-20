package com.lb.basic;

public interface UserDao {
    void save(User user);

    void findByNameAndPassword(String name, String password);
}
