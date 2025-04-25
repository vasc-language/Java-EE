package org.example.jd20250417springbookdemo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.example.jd20250417springbookdemo.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Created with IntelliJ IDEA.
 * Description: 统一数据返回
 * User: 姚东名
 * Date: 2025-04-25
 * Time: 15:22
 */

/**
 * 核心功能
 * 这个类实现了 Spring 框架的 ResponseAdvice 接口，用于在控制器方法返回值写入响应体之前进行拦截和处理
 * 通过 @ControllerAdvice 注释，它可以拦截所有控制器的响应
 */
@ControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice {
    /**
     * ObjectMapper 的作用，主要是用于 Java 对象和 JSON 数据之间相互转换
     * - Java 对象 -> JSON 字符串（序列化）：将一个人 Java 对象（比如 Result 对象）转换成一个 JSON 格式的字符串
     * - JSON 字符串 -> Java 对象（反序列化）：将一个 JSON 格式的字符串转换成一个对应的你Java 对象
     */
    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        // false 不处理 true 处理
        // 当前设置为 false，表示实际上没有启动拦截处理
        // 若要启动，需要将返回值改为 true
        return false;
    }

    /**
     *
     * @param body 控制器方法的返回值
     * @param returnType 控制器方法返回的元信息
     * @param selectedContentType 选定的响应内容类型
     * @param selectedConverterType 选定的消息转换器类型
     * @param request 当前的 HTTP 请求
     * @param response 当前的 HTTP 响应
     * @return
     */
    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        /**
         * 这个方法处理控制器返回的数据，将其转化为统一的 Result 对象格式
         * - 如果返回值是字符串，使用后 ObjectMapper 将其转化为 JSON 字符串的 Result 对象
         * - 如果返回值已经是 Result 对象，则直接返回
         * - 其他情况，将返回值包装在 Result.success(body)
         */
        if (body instanceof String) {
            /**
             * - 当控制器返回一个普通的 String 类型时，ResponseAdvice 会拦截到这个 String
             * - 为了统一返回格式，它需要将这个 String 包装进 Result 对象中（Result.success(body)）
             * - 但是，HTTP 响应体通常需要返回 JSON 格式。如果直接返回 Result.success(body) 这个 Java 对象，
             *   Spring 框架可能不知道如何将其正确序列化，特别是当期望的是 contentType = "application/json"
             *   时，可能会与原始 String 的处理方式冲突
             * - 因此，这里显式地使用 @Autowired 注入地 ObjectMapper 实例，调用writeValueAsString() 方法，
             *   将 Result.success(body) 这个 Result 对象序列化为一个 JSON 字符串
             * - 这样，即使原始返回值是 String 类型，最终写入 HTTP 响应体也是符合统一 Result 结构的 JSON 格式
             */
            return objectMapper.writeValueAsString(Result.success(body));
        }
        if (body instanceof Result) {
            return body;
        }
        // return null;
        return Result.success(body);
    }

}
