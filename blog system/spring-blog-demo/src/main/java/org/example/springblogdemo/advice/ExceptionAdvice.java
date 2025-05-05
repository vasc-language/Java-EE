package org.example.springblogdemo.advice;

import lombok.extern.slf4j.Slf4j;
import org.example.springblogdemo.exception.BlogException;
import org.example.springblogdemo.pojo.response.Result;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-04
 * Time: 22:07
 */

@Slf4j
@ResponseBody
@ControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler
    public Result exceptionHandler(Exception exception){
        log.error("发生异常, e: ", exception);
        return Result.fail(exception.getMessage());
    }

    @ExceptionHandler
    public Result exceptionHandler(BlogException exception){
        log.error("发生异常, e: {}", exception.getErrMsg());
        return Result.fail(exception.getErrMsg());
    }

    /**
     * - 作用：当 Spring Web 应用中控制器方法的参数校验失败时，会抛出此异常
     * - 使用场景：主要用于通过 @Valid 或 @Validated 注解进行的参数校验失败的情况，特别是在 Spring web 应用程序中，当请求的参数不符合预
     *     定义的验证规则时
     * - 异常处理机制
     *   1. 客户端发送包含无效的数据的请求
     *   2. Spring MVC 尝试请求数据绑定到控制器方法参数上
     *   3. 由于 @Valid 注解的存在，Spring 会对参数进行验证
     *   4. 验证失败时，会抛出 HandlerMethodValidatedException
     *   5. 定义的 @ExceptionHandler 方法捕获
     *   6. 记录错误日志并返回自定义错误响应
     * @param exception
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(HandlerMethodValidationException exception){
        log.error("发生异常, e: {}", exception.getMessage());
        return Result.fail("参数校验失败");
    }

    /**
     * 用于处理参数校验失败的情况。当使用 @Valid 或 @Validated 注解对请求参数进行校验，且校验失败时，会抛出此异常
     * @param exception
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(MethodArgumentNotValidException exception){
        /**
         * - getBindingResult() 方法返回 BindingResult 对象，它包含了验证过程中产生的所有错误信息。BindingResult 是 Spring 框架中
         *   用于存储数据绑定和验证错误的接口
         * - getFieldError() 方法返回 FieldError 对象，它代表数据绑定和验证过程中发生的第一个字段错误。如果有多个字段错误，此方法只返回
         *   第一个
         * - getDefaultMessage() 方法返回错误的默认消息，这通常是在验证注解中通过 message 属性定义的消息
         *
         * - 完整流程
         * 整个方法的执行流程是：
         *   1. 捕获到 MethodArgumentNotValidException 异常
         *   2. 从异常中获取绑定对象的结果对象
         *   3. 从绑定结果中获取第一个字段错误
         *   4. 从字段错误中获取默认的错误消息
         *   5. 记录错误日志
         *   6. 返回包含错误消息的失败结果
         */
        // 添加空指针检查
        FieldError fieldError = exception.getBindingResult().getFieldError();
        String msg = fieldError != null ? fieldError.getDefaultMessage() : "参数校验失败";
        
        log.error("发生异常, e: {}", exception.getMessage());
        return Result.fail(msg);
    }

    /*@ExceptionHandler
    public Result exceptionHandler(MethodArgumentNotValidException exception){
        //TODO 空指针自行处理
        String msg = exception.getBindingResult().getFieldError().getDefaultMessage();
        log.error("发生异常, e: {}", exception.getMessage());
        return Result.fail(msg);
    }*/
}
