package com.bytedance.leadnews.config;

import com.bytedance.leadnews.common.config.JacksonObjectMapper;
import com.bytedance.leadnews.common.config.WebConfig;
import com.bytedance.leadnews.interceptor.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WmWebConfig implements WebMvcConfigurer {
    @Autowired
    private Authorization authorization;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorization).addPathPatterns("/**");
    }
}
