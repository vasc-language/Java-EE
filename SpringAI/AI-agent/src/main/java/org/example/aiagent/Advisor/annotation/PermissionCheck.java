package org.example.aiagent.Advisor.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限校验注解
 * 用于标记需要进行权限校验的方法
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface PermissionCheck {
    /**
     * 需要的权限类型
     */
    String[] value() default {};
    
    /**
     * 是否允许跳过校验
     */
    boolean skipCheck() default false;
} 