package com.lb.proxy.dynamic;

public class UserServiceImpl implements UserService {

    @Override
    public void register(User user) {
        System.out.println("UserServiceImpl.register");
    }

    @Override
    public boolean login(String username, String password) {
        System.out.println("UserServiceImpl.login");
        return true;
    }
}
