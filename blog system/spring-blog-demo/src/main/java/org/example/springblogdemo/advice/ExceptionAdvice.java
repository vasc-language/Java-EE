package org.example.springblogdemo.advice;

import lombok.extern.slf4j.Slf4j;
import org.example.springblogdemo.exception.BlogException;
import org.example.springblogdemo.pojo.response.Result;
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
    @ExceptionHandler
    public Result exceptionHandler(HandlerMethodValidationException exception){
        log.error("发生异常, e: {}", exception.getMessage());
        return Result.fail("参数校验失败");
    }
    @ExceptionHandler
    public Result exceptionHandler(MethodArgumentNotValidException exception){
        // TODO 空指针自行处理
        String msg = exception.getBindingResult().getFieldError().getDefaultMessage();
        log.error("发生异常, e: {}", exception.getMessage());
        return Result.fail(msg);
    }
}
