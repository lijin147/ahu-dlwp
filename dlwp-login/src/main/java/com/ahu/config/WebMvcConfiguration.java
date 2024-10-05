package com.ahu.config;

import com.ahu.interceptor.JWTInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


/**
 * 将JWT拦截器进行注册
 */
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {
    @Autowired
    private JWTInterceptor jwtInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .excludePathPatterns("/login/login");
    }
}
