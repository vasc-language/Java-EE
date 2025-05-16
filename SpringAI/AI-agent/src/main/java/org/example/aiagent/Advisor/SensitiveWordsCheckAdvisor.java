package org.example.aiagent.Advisor;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.example.aiagent.Advisor.annotation.SensitiveWordsCheck;
import org.example.aiagent.Advisor.exception.SensitiveWordsException;
import org.example.aiagent.Advisor.util.SensitiveWordsUtil;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;

/**
 * 敏感词校验切面
 * 拦截添加了@SensitiveWordsCheck注解的方法，检查方法参数中是否包含敏感词
 */
@Slf4j
@Aspect
@Component
@Order(2) // 设置切面优先级，数字越小优先级越高
public class SensitiveWordsCheckAdvisor {
    
    /**
     * 定义切点 - 拦截所有带有@SensitiveWordsCheck注解的方法
     */
    @Pointcut("@annotation(org.example.aiagent.Advisor.annotation.SensitiveWordsCheck)")
    public void sensitiveWordsCheckPointcut() {
    }
    
    /**
     * 环绕通知 - 方法执行前后进行拦截
     *
     * @param joinPoint 连接点
     * @return 方法执行结果
     * @throws Throwable 异常
     */
    @Around("sensitiveWordsCheckPointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("开始检查敏感词: {}", joinPoint.getSignature().getName());
        
        try {
            // 方法执行前检查敏感词
            checkSensitiveWords(joinPoint);
            
            // 执行目标方法
            Object result = joinPoint.proceed();
            
            log.info("方法 [{}] 执行完成，未检测到敏感词", joinPoint.getSignature().getName());
            
            return result;
        } catch (SensitiveWordsException e) {
            // 敏感词异常日志
            log.error("敏感词检查失败: {}，敏感内容: {}", e.getMessage(), e.getSensitiveContent());
            throw e;
        } catch (Throwable e) {
            // 其他异常日志
            log.error("方法 [{}] 执行异常: {}", joinPoint.getSignature().getName(), e.getMessage(), e);
            throw e;
        }
    }
    
    /**
     * 检查参数中是否包含敏感词
     *
     * @param joinPoint 连接点
     * @throws SensitiveWordsException 包含敏感词时抛出异常
     */
    private void checkSensitiveWords(JoinPoint joinPoint) throws SensitiveWordsException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Parameter[] parameters = method.getParameters();
        Object[] args = joinPoint.getArgs();
        
        // 获取注解信息
        SensitiveWordsCheck annotation = method.getAnnotation(SensitiveWordsCheck.class);
        SensitiveWordsCheck.CheckLevel level = annotation.level();
        String[] paramNames = annotation.paramNames();
        
        // 如果未指定参数名，则检查所有String类型参数
        boolean checkAll = paramNames.length == 0;
        
        log.info("检查方法: [{}]，检查级别: {}，检查参数: {}", 
                method.getName(), level, checkAll ? "全部String参数" : Arrays.toString(paramNames));
        
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            Object arg = args[i];
            
            // 只检查String类型参数
            if (arg instanceof String) {
                String paramName = parameter.getName();
                String stringArg = (String) arg;
                
                // 检查是否需要检查当前参数
                if (checkAll || Arrays.asList(paramNames).contains(paramName)) {
                    log.debug("检查参数: {}, 值: {}", paramName, stringArg);
                    
                    // 检查是否包含敏感词
                    String sensitiveWord = SensitiveWordsUtil.checkSensitiveWords(stringArg);
                    if (sensitiveWord != null) {
                        String message = String.format("参数[%s]中包含敏感词[%s]", paramName, sensitiveWord);
                        log.warn(message);
                        
                        // 根据检查级别决定是警告还是抛出异常
                        if (level == SensitiveWordsCheck.CheckLevel.ERROR) {
                            throw new SensitiveWordsException(message, stringArg);
                        }
                    }
                }
            }
        }
        
        log.info("敏感词检查通过");
    }
} 