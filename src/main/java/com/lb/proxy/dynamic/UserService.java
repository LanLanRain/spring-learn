package com.lb.proxy.dynamic;

public interface UserService {
    void register(User user);

    boolean login(String username, String password);
}
