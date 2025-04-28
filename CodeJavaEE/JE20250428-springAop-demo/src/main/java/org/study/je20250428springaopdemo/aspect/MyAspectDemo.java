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
