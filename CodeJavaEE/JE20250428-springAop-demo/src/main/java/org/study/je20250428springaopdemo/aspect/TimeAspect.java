package org.study.je20250428springaopdemo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-28
 * Time: 7:49
 */
@Slf4j
@Aspect
@Component
public class TimeAspect {

    @Pointcut("execution(* org.study.je20250428springaopdemo.controller.*.*(..))")
    public Object timeRecord(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 1. 记录开始时间
        long start = System.currentTimeMillis();
        // 2. 执行 controller 路径下的所有方法
        Object proceed = proceedingJoinPoint.proceed();
        // 3. 记录结束时间
        long end = System.currentTimeMillis();
        log.info(proceedingJoinPoint.getSignature().toString() + "耗时：" + (end - start) + "ms");

        return proceed;
    }
}
