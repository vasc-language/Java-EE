package org.study.je20250428springaopdemo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-28
 * Time: 8:26
 */
@Slf4j
@Order(3)
@Aspect
// @Component
// 为了测试不混乱
public class AspectDemo2 {
    @Before("execution(* org.study.je20250428springaopdemo.controller.*.*(..))")
    public void doBefore() {
        log.info("AspectDemo2 doBefore~");
    }

    @After("execution(* org.study.je20250428springaopdemo.controller.*.*(..))")
    public void doAfter() {
        log.info("AspectDemo2 doAfter~");
    }
}
