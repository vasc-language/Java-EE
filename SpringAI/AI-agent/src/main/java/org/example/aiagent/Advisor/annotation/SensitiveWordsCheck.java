package org.example.aiagent.Advisor.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 敏感词校验注解
 * 用于标记需要进行敏感词检查的方法
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface SensitiveWordsCheck {
    /**
     * 需要检查的参数名称
     */
    String[] paramNames() default {};
    
    /**
     * 检查级别：WARN-仅警告，ERROR-抛出异常
     */
    CheckLevel level() default CheckLevel.ERROR;
    
    /**
     * 检查级别枚举
     */
    enum CheckLevel {
        WARN, ERROR
    }
} 