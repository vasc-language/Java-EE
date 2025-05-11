package org.example.springblogdemo2.common.config;

import org.example.springblogdemo2.common.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-09
 * Time: 22:23
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                // 先只拦截指定的API路径，避免拦截静态资源和登录接口
                .addPathPatterns("/blog/**", "/user/**")
                // 明确排除登录相关的路径
                .excludePathPatterns(
                        "/user/login/**",      // 排除所有登录API（任何HTTP方法）
                        "/blog_login.html"     // 排除登录页面
                );
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 静态资源处理
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
    }
}