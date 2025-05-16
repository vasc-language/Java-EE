package org.example.aiagent.Advisor;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.example.aiagent.Advisor.annotation.PermissionCheck;
import org.example.aiagent.Advisor.exception.PermissionDeniedException;
import org.example.aiagent.Advisor.util.SecurityContextUtil;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 权限校验切面
 * 拦截添加了@PermissionCheck注解的方法，进行权限校验
 */
@Slf4j
@Aspect
@Component
@Order(1) // 设置切面优先级，数字越小优先级越高
public class PermissionCheckAdvisor {
    
    /**
     * 定义切点 - 拦截所有带有@PermissionCheck注解的方法
     */
    @Pointcut("@annotation(org.example.aiagent.Advisor.annotation.PermissionCheck)")
    public void permissionCheckPointcut() {
    }
    
    /**
     * 环绕通知 - 方法执行前后进行拦截
     *
     * @param joinPoint 连接点
     * @return 方法执行结果
     * @throws Throwable 异常
     */
    @Around("permissionCheckPointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        
        try {
            // 方法执行前进行权限检查
            checkPermission(joinPoint);
            
            // 执行目标方法
            Object result = joinPoint.proceed();
            
            // 方法执行后记录时间
            long executionTime = System.currentTimeMillis() - startTime;
            log.info("方法 [{}] 执行完成，耗时: {} ms", joinPoint.getSignature().getName(), executionTime);
            
            return result;
        } catch (PermissionDeniedException e) {
            // 权限异常日志
            log.error("权限校验失败: {}", e.getMessage());
            throw e;
        } catch (Throwable e) {
            // 其他异常日志
            log.error("方法 [{}] 执行异常: {}", joinPoint.getSignature().getName(), e.getMessage(), e);
            throw e;
        }
    }
    
    /**
     * 检查用户权限
     *
     * @param joinPoint 连接点
     * @throws PermissionDeniedException 权限不足时抛出异常
     */
    private void checkPermission(JoinPoint joinPoint) throws PermissionDeniedException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        
        // 获取注解信息
        PermissionCheck permissionCheck = method.getAnnotation(PermissionCheck.class);
        if (permissionCheck.skipCheck()) {
            log.info("方法 [{}] 跳过权限校验", method.getName());
            return;
        }
        
        String[] requiredPermissions = permissionCheck.value();
        log.info("检查用户权限，方法: [{}]，需要权限: {}", method.getName(), Arrays.toString(requiredPermissions));
        
        // 检查用户是否具有所需权限
        if (!SecurityContextUtil.hasPermission(requiredPermissions)) {
            String errorMsg = String.format(
                    "权限不足，当前用户权限: %s，需要权限: %s",
                    SecurityContextUtil.getCurrentUserPermissions(),
                    Arrays.toString(requiredPermissions)
            );
            log.warn(errorMsg);
            throw new PermissionDeniedException(errorMsg);
        }
        
        log.info("用户权限校验通过");
    }
} 