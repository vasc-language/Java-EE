package org.example.je20250307springbook.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * Description: 登陆界面 login
 * User: 姚东名
 * Date: 2025-03-06
 * Time: 20:22
 */
@RequestMapping("/user")
@RestController
public class UserController {
    @PostMapping("/login")
    public Boolean login(String userName, String password, HttpSession session) {
        // 1. 校验参数
        // 2. 验证用户名和密码
        // 3. 存储 Session
        // 4. 返回
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
