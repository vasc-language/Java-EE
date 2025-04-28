package org.study.je20250428springaopdemo.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-28
 * Time: 8:41
 */

/**
 * @Retention(RetentionPolicy.RUNTIME) 指定注解的保留策略
 * - SOURCE：注解之灾源代码中保留，编译后丢失
 * - CLASS：注解保留在 .class 文件中，但是在运行时（JVM）不可用
 * - RUNTIME：注解保留在 .class 文件中，并且在运行时可以通过反射机制获取
 *
 * Spring AOP 是在运行时通过检查方法上的注解来工作的，所以必须设置为 RUNTIME，否则 AOP 框架无法在运行时识别到你的自定义注解
 * @Target(...) 这个注解指定了你的注解可以在哪些程序元素。这是 AOP 方法拦截最常见的目标
 * - ElementType.METHOD：(method)表示注解可以应用于方法。这是 AOP 方法拦截最常用的目标
 * - ElementType.TYPE：(type)表示注解可以应用于类、接口、枚举
 * - ElementType.FIELD：(field)表示注解可以用于字段
 * - ElementType.PARAMETER：(parameter)表示注解可以用于方法参数
 * - ElementType.ANNOTATION_TYPE：(annotation_type)表示注解可以应用于其他注解类型（使其成为元注解）
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
public @interface MyAspect {
    // 定义注解类型需要使用 @interface 关键字，而不是 class 或 interface 
}
