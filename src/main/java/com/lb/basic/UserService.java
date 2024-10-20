package com.lb.basic;

public interface UserService {
    void register(User user);

    void login(String username, String password);
}
