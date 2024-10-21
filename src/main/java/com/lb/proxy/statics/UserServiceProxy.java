package com.lb.proxy.statics;

import com.lb.proxy.statics.User;

public class UserServiceProxy implements UserService {

    private UserServiceImpl userServiceImpl = new UserServiceImpl();


    @Override
    public void register(User user) {
        System.out.println("---log---");
        userServiceImpl.register(user);
    }

    @Override
    public boolean login(String username, String password) {
        System.out.println("---log---");
        return userServiceImpl.login(username, password);
    }
}
