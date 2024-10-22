package com.lb.aspect;

import com.lb.basic.User;

public interface UserService {
    void register(User user);

    void login(String username, String password);
}