package com.maijianda.cloud_library.controller;

import com.maijianda.cloud_library.annotation.RequireRole;
import com.maijianda.cloud_library.common.Result;
import com.maijianda.cloud_library.entity.User;
import com.maijianda.cloud_library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result<Void> register(@RequestBody User user) {
        userService.register(user);
        return Result.success("注册成功", null);
    }
    
    @PostMapping("/login")
    public Result<User> login(
            @RequestBody User user,
            HttpSession session
    ) {
        User loginUser = userService.login(user.getUsername(), user.getPassword());
        session.setAttribute("loginUser", loginUser);
        return Result.success("登录成功", loginUser);
    }
    
    @PostMapping("/logout")
    public Result<Void> logout(HttpSession session) {
        session.invalidate();
        return Result.success("注销成功", null);
    }
    
    @GetMapping("/me")
    public Result<User> currentUser(HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        if (user == null) {
            return Result.fail("未登录");
        }
        return Result.success(user);
    }
}
