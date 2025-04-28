package org.study.je20250428springaopdemo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-28
 * Time: 7:45
 */
@Slf4j
@Order(1)
@Aspect
@Component
public class AspectDemo1 {
    /**
     * 通知类型
     * @Around：环绕通知，这是功能最强大的通知类型。它可以在目标方法执之前和之后都执行自定义逻辑。他甚至可以决定是否执行目标方法，或者修改目标方法的返回值
     * @Before：前置通知，此注解标注的通知方法在目标方法前被执行
     * @After：后置通知，此注解标注的通知方法在目标方法后被执行
     * @AfterReturning：返回后通知，此注解的通知方法在目标方法后被执行，有异常不会被执行
     * @AfterThrowing：异常后通知，此注解的通知方法发生异常后被执行
     */
    // pt() 方法，主要目的是作为一个命名的切点引用，通过 @Pointcut 注解将一个切点表达式赋予给这个方法名 pt
    // 切点表达式：execution(* org.study.je20250428springaopdemo.controller.*.*(..))
    // execution：常用的切点指示符，表示匹配方法的执行
    // 这个切点表达式 controller 包下所有的类的公共方法的执行
    @Pointcut("execution(* org.study.je20250428springaopdemo.controller.*.*(..))")
    public void pt(){};

    @Around("pt()")
    public Object recordTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 记录方法的开始执行
        log.info("目标方法执行前~");
        Object result = null;
        try {
            // 捕获异常
            result = proceedingJoinPoint.proceed();
        } catch (Exception e) {
            log.error("do Around throwing~");
        }
        // 记录方法的执行结束
        log.info("目标方法执行后~");

        return result;
    }

    @Before("pt()")
    public void doBefore() {
        log.info("doBefore~");
    }

    @After("pt()")
    public void doAfter() {
        log.info("doAfter~");
    }

    @AfterReturning("pt()")
    public void doAfterReturning() {
        log.info("doAfterReturning~");
    }

    @AfterThrowing("pt()")
    public void doAfterThrowing() {
        log.info("doAfterThrowing~");
    }
}
