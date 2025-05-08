package org.example.springblogdemo.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.springblogdemo.pojo.request.UserLoginRequest;
import org.example.springblogdemo.pojo.response.UserLoginResponse;
import org.example.springblogdemo.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-06
 * Time: 11:31
 */
@Slf4j
@RequestMapping("/user")
@RestController
public class UserController {
    @Resource(name = "userServiceImpl")
    private UserService userService;

    /**
     * 因为注解上使用了 @RequestBody 这个注解是专门用于接收和处理 HTTP 请求中的 JSON 数据
     * 当 SpringBoot 应用收到请求时，会自动将 JSON 数据反序列化为 Java 对象
     * @param userLoginRequest 前端以 JSON 格式发送来的请求
     * @return
     */
    @RequestMapping("/login")
    public UserLoginResponse login(@RequestBody @Validated UserLoginRequest userLoginRequest) {
        log.info("用户登录，用户名：" + userLoginRequest.getUserName());
        return userService.checkPassword(userLoginRequest);
    }
}
