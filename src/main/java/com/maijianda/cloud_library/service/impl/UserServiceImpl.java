package com.maijianda.cloud_library.service.impl;

import com.maijianda.cloud_library.entity.User;
import com.maijianda.cloud_library.mapper.UserMapper;
import com.maijianda.cloud_library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public void register(User user) {
        User existUser = userMapper.selectUserByUsername(user.getUsername());
        if (existUser != null) {
            throw new RuntimeException("Username already exists");
        }
        user.setRole("USER");
        userMapper.insertUser(user);
    }

    @Override
    public User login(String username, String password) {
        User user = userMapper.selectUserByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid username or password");
        }
        return user;
    }

    @Override
    public User getByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }
}
