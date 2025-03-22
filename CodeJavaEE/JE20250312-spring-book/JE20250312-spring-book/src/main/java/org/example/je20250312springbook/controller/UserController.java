package org.example.je20250312springbook.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-03-12
 * Time: 19:54
 */

/**
 * 用户请求
 * -> Controller：处理 HTTP 请求，路由到正确的业务逻辑
 * -> Service：实现业务逻辑，协调多个操作
 * -> DAO：执行数据库操作
 * -> Database：数据库，记录储存数据
 * -> Model：在各层之间传递的数据对象
 *
 * 举个例子：
 * 客户 -> 填写注册表单 -> 点击提交
 * 前台小美(Controller) -> 接收表单数据 -> 转换为 Customer 对象
 * 王经理(Service) ->验证数据 -> 处理业务逻辑
 * 小李(DAO) -> 将数据存入数据库中
 * 数据以 Customer(Model) 形式在各层之间传递
 *
 */
@RequestMapping("/user")
@RestController
public class UserController {
    @PostMapping("/login")
    public boolean login(String userName, String password, HttpSession session) {
        // 1. 校验参数是否合规
        // 2. 输入是否正确
        // 3. 设置 session
        // 4. 返回
        if (!StringUtils.hasLength("userName") || !StringUtils.hasLength("password")) {
            return false;
        }
        // 账号+密码都设置成 admin
        if ("admin".equals(userName) && "admin".equals(password)) {
            session.setAttribute("userName", userName);
            return true;
        }
        return false;
    }
}
