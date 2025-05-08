package org.example.springblogdemo2.common.advice;

import lombok.extern.slf4j.Slf4j;
import org.example.springblogdemo2.common.exception.BlogException;
import org.example.springblogdemo2.pojo.response.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-08
 * Time: 22:22
 */
@Slf4j
@ResponseBody
@ControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler
    public Result exceptionHandler(Exception exception) {
        log.error("发生异常，e: ", exception);
        return Result.fail(exception.getMessage());
    }

    @ExceptionHandler
    public Result exceptionHandler(BlogException blogException) {
        log.error("发生异常，e: " + blogException);
        return Result.fail(blogException.getErrMsg());
    }
}
