package spring.book.je20250422springbookdemo.controller;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.book.je20250422springbookdemo.contant.Contants;
import spring.book.je20250422springbookdemo.model.Result;
import spring.book.je20250422springbookdemo.model.UserInfo;
import spring.book.je20250422springbookdemo.service.UserInfoService;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-22
 * Time: 22:16
 */
@Slf4j
@RequestMapping("/user")
@RestController
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    /**
     *
     * @param username 接收前端发送的请求（用户名）
     * @param password 接收前端发送的请求（密码）
     * @param session 存储用户信息
     * @return
     */
    @RequestMapping("/login")
    public Result login(String username, String password, HttpSession session) {
        /**
         * 验证步骤
         * 1. 校验参数是否合法
         * 2. 验证用户名和密码是否正确
         * 3. 设置 session
         * 4. 返回结果
         */
        log.info("username: " + username);
        if (!StringUtils.hasLength(username) || !StringUtils.hasLength(password)) {
            return Result.fail("用户名或密码不能为空");
        }

        // 根据用户名，获取用户信息
        UserInfo userInfo = userInfoService.QueryUserInfoByName(username); // 进行比较，看是否输入正确
        if (userInfo == null) {
            return Result.fail("用户名不存在");
        }
        // 再判断密码是否一致
        if (password.equals(userInfo.getPassword())) {
            userInfo.setPassword("");
            session.setAttribute(Contants.SESSION_USER_KEY, userInfo);
            return Result.success(true);
        }

        return Result.fail("密码错误");
    }
}
