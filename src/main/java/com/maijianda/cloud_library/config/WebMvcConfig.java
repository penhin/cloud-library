package com.maijianda.cloud_library.config;

import com.maijianda.cloud_library.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    
    @Override
    public void addInterceptors(InterceptorRegistry registery) {
        registery.addInterceptor(new LoginInterceptor())
            .addPathPatterns("/api/**")
            .excludePathPatterns(
                "/api/auth/**",
                "/error"  
            );
    }
}
