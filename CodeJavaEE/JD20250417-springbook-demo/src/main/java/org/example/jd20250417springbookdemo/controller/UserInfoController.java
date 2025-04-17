package org.example.jd20250417springbookdemo.controller;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.example.jd20250417springbookdemo.contant.Constants;
import org.example.jd20250417springbookdemo.model.UserInfo;
import org.example.jd20250417springbookdemo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-17
 * Time: 21:32
 */
@Slf4j
@RequestMapping("/user")
@RestController
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    /**
     * 用户登录界面
     * @param username 接受前端传递过来的用户名
     * @param password 接受前端传递过来的密码
     * @param session 使用 session 存储
     * @return
     */
    @RequestMapping("/login")
    public boolean login(String username, String password, HttpSession session) {
        /**
         * 验证步骤
         * 1. 参数是否规范
         * 2. 检验用户名和密码是否正确
         * 3. 设置session
         * 4. 返回结果
         */
        log.info("username: " + username);
        if (!StringUtils.hasLength(username) || !StringUtils.hasLength(password)) {
            return false;
        }
        // 客户端输入的参数和数据库中的参数进行比较
        UserInfo userInfo = userInfoService.queryUserNameByName(username);
        // 1. 先确认用户是否存在 2. 再确认密码是否正确
        if (userInfo == null) {
            return false;
        }
        if (password.equals(userInfo.getPassword())) {
            // 清空密码
            userInfo.setPassword("");
            session.setAttribute(Constants.SESSION_USER_KEY, userInfo);
            return true;
        }
        return false;
    }
}
