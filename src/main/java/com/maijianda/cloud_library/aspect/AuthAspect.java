package com.maijianda.cloud_library.aspect;

import com.maijianda.cloud_library.annotation.RequireRole;
import com.maijianda.cloud_library.entity.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class AuthAspect {

    @Autowired
    private HttpServletRequest request;

    @Before("@annotation(requireRole)")
    public void checkRole(RequireRole requireRole) {

        HttpSession session = request.getSession(false);
        if (session == null) {
            throw new RuntimeException("未登录");
        }

        User user = (User) session.getAttribute("loginUser");
        if (user == null) {
            throw new RuntimeException("未登录");
        }

        String needRole = requireRole.value();
        if (!needRole.equals(user.getRole())) {
            throw new RuntimeException("当前的身份组无法进行此操作");
        }
    }
}

