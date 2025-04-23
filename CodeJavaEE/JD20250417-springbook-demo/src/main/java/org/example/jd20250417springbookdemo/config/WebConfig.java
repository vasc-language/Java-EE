package org.example.jd20250417springbookdemo.config;

import org.example.jd20250417springbookdemo.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created with IntelliJ IDEA.
 * Description: 配置拦截器
 * User: 姚东名
 * Date: 2025-04-23
 * Time: 18:07
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // WebMvcConfigurer.super.addInterceptors(registry);
        registry.addInterceptor(loginInterceptor).addPathPatterns("/book/**"); // 拦截 /book 目录下的所有方法
    }
}
