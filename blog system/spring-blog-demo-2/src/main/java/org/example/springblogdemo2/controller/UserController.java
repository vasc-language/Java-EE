package org.example.springblogdemo2.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.springblogdemo2.pojo.request.UserLoginRequest;
import org.example.springblogdemo2.pojo.response.UserLoginResponse;
import org.example.springblogdemo2.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-09
 * Time: 19:08
 */
@Slf4j
@RequestMapping("/user")
@RestController // @Controller + @ResponseBody 用来标识这个控制器会直接将返回值序列化为HTTP1响应体（通常是 JSON 格式）
public class UserController {
    @Resource(name = "userServiceImpl")
    private UserService userService;

    /**
     * @RequestBody 是一个方法的参数级别的注释，用于指示 Spring 将 HTTP 请求反序列化为Java对象
     * 这是因为在接收客户端发送过来的 JSON 数据时，使用 @RequestBody 注释来标记那些从请求体中解析这些参数
     * @param userLoginRequest
     * @return
     */
    @RequestMapping("/login")
    public UserLoginResponse login(@RequestBody @Validated UserLoginRequest userLoginRequest) {
        log.info("用户登录，用户名：" + userLoginRequest.getUsername());
        return userService.checkPassword(userLoginRequest);
    }
}
