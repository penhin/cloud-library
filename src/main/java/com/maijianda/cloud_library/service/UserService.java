package com.maijianda.cloud_library.service;

import com.maijianda.cloud_library.entity.User;

public interface UserService {
    void register(User user);

    User login(String username, String password);

    User getByUsername(String username);
}
