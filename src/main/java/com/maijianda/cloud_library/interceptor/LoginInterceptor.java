package com.maijianda.cloud_library.interceptor;

import com.maijianda.cloud_library.common.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws IOException {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("loginUser") != null) {
            return true;
        }

        response.setContentType("application/json;charset=UTF-8");

        Result<Void> result = Result.fail("未登录，请先登录");

        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(result));
        
        return false;
    }
}
