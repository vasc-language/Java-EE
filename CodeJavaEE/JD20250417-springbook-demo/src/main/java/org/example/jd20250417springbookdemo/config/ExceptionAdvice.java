package org.example.jd20250417springbookdemo.config;

import lombok.extern.slf4j.Slf4j;
import org.example.jd20250417springbookdemo.model.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created with IntelliJ IDEA.
 * Description: 实现统一异常处理和返回格式
 * User: 姚东名
 * Date: 2025-04-25
 * Time: 15:33
 */


/**
 * 详细介绍 @ExceptionAdvice 注释以及它在 ExceptionAdvice 中如何实现统一异常处理和返回格式
 * @ExceptionHandle 是 SpringMCV 提供的一个注释，用于标记一个方法，使其能够处理在同一个控制器（Controller）
 * 或全局（通过@ControllerAdvice）中由请求处理方法（如 RequestMapper，@GetMapper 等标记的方法）抛出的特定类型的异常
 * 1. 异常捕获：
 * 2. 处理特定异常：
 * 3. 异常处理逻辑：
 * 4. 方法签名：
 * 5. 使用位置：
 *
 * 总结：
 * @ExceptionHandle 注释使得开发者可以在特定的方法集中处理指定类型的异常。通过将这些方法放在 @RestControllerAdvice 类中，
 * 可以实现全局异常处理。结合 Result 这样的统一响应模型，当异常发生时，全局异常处理器（RestControllerAdvice）捕获异常，记录
 * 日志，然后构造并返回一个符合格式的 Result 对象，从而保证了即使在出错的情况下，API 的响应的格式依然保持一致和友好，避免向客户
 * 端暴露底层异常细节，这是构建健壮、易于维护的 RESTful API 的常用实践 restful
 */
@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(Exception.class)
    public Result handler(Exception e) {
        log.error("发生内部异常，e: ", e);
        return Result.fail("内部异常，请联系管理员");
    }

    @ExceptionHandler(NullPointerException.class)
    public Result handler2(Exception e) {
        log.error("发生内部异常，e: ", e);
        return Result.fail("发生空指针异常，请联系管理员");
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    public Result handler3(Exception e) {
        log.error("发生内部异常，e: ", e);
        return Result.fail("发生数组越界异常，请联系管理员");
    }
}
