package com.lb.proxy.statics;

import com.lb.proxy.statics.User;

public interface UserService {
    void register(User user);

    boolean login(String username, String password);
}
