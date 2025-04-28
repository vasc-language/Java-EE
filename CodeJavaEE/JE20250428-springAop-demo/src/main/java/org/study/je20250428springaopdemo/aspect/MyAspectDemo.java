package org.study.je20250428springaopdemo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-28
 * Time: 8:44
 */

@Slf4j
@Aspect
// @Component
public class MyAspectDemo {
    /**
     * 拦截（Around）任何被 org.study.je20250428springaopdemo.aspect.MyAspect 这个注解所标注的方法的执行
     * - @annotation() 切点指示符是专门用来匹配那些带有特定的注释的方法
     * - 它的语法就是 @annotation(@annotation(com-yourCompany-yourApp.YourCustom Annotation)
     * - 这清晰地表达出了你的意图：我想要增强（Advice）所有被 YourCustomAnnotation 标注的方法
     *
     * 对比
     * execution 和 @annotation 是为了解决不同匹配问题而设计的
     * - 当需要根据方法签名（结构）来选择连接点时，使用 execution()
     * - 当你需要根据方法是否被特定注释标记（元数据）来选择连接点时，必须使用 @annotation()
     */
    @Around("@annotation(org.study.je20250428springaopdemo.aspect.MyAspect)")
    public Object recordTime(ProceedingJoinPoint pjp) {
        log.info("目标方法执行前~");
        // 执行目标方法
        Object result = null;
        try {
            result = pjp.proceed();
        } catch (Throwable throwable) {
            log.error("do Around throwing~");
        }
        log.info("目标方法执行后~");

        return result;
    }
}
