package com.example.je20250306springbook.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-03-06
 * Time: 9:03
 */
@RequestMapping("/user")
@RestController
public class UserController {
    @RequestMapping("/login")
    public Boolean login(String userName, String password, HttpSession session) {
        // 参数校验
        // 验证用户名和密码
        // 设置 session
        // 返回结果
        if (!StringUtils.hasLength(userName) || !StringUtils.hasLength(password)) {
            return false;
        }

        if ("admin".equals(userName) && "admin".equals(password)) {
            session.setAttribute("userName", userName);
            return true;
        }
        return false;
    }
}
