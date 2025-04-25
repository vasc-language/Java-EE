package spring.book.je20250422springbookdemo.config;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import spring.book.je20250422springbookdemo.model.Result;

/**
 * Created with IntelliJ IDEA.
 * Description: 将异常统一处理，返回同一种形式
 * User: 姚东名
 * Date: 2025-04-25
 * Time: 21:45
 */
@Slf4j
@ControllerAdvice
@ResponseBody
public class ExceptionAdvice {
    @ExceptionHandler(Exception.class)
    public Result handler(Exception e) {
        log.error("发生异常，e: ", e);
        return Result.fail("内部错误，请联系管理员");
    }

    @ExceptionHandler(NullPointerException.class)
    public Result handler2(Exception e) {
        log.error("发生异常，e: ", e);
        return Result.fail("发生空指针异常，请联系管理员");
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    public Result handler3(Exception e) {
        log.error("发生异常，e: ", e);
        return Result.fail("发生数组越界异常，请联系管理员");
    }
}
