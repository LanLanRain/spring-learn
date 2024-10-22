package com.lb.cglib;

import com.lb.proxy.dynamic.User;

public class UserService {
    public void register(User user) {
        System.out.println("注册用户：" + user);
    }

    public void login(String username, String password) {
        System.out.println("登录用户：" + username + "，密码：" + password);
    }
}
