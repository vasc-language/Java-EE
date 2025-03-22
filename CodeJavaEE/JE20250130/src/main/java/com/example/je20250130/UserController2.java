package com.example.je20250130;

import jakarta.servlet.http.HttpSession;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * Description: 需求: 用户输⼊账号和密码, 后端进行校验密码是否正确
 * User: 姚东名
 * Date: 2025-02-13
 * Time: 23:39
 */
@RequestMapping("/UserController2")
@RestController
public class UserController2 {
    @RequestMapping("/login")
    public boolean login(
            @RequestParam("userName") String userName,
            @RequestParam("password") String password,
            HttpSession session
    ) {
        // 参数验证
        /*if (userName == null || userName == "" || password == null || password == "") {
            return false;
        }*/
        if (!StringUtils.hasLength(userName) || !StringUtils.hasLength(password)) {
            return false;
        }
        // 用户名 和 密码进行校验
        if ("admin".equals(userName) && "123456".equals(password)) {
            session.setAttribute("userName", userName);
            return true;
        }
        return false;
    }

    // 获取用户名
    @RequestMapping("/getLoginUser")
    public String getLoginUser(HttpSession session) {
        String userName = (String) session.getAttribute("userName");
        return userName;
    }
}
