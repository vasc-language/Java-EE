package com.example.je20250130;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-02-10
 * Time: 17:10
 */
@Controller
@RequestMapping("/response")
public class RespController {
    // 返回页面
    @RequestMapping("/r1")
    public String returnPage() {
        return "D:/Java/JavaEE/webapp/index.jsp";
    }

    // 返回数据
    @ResponseBody
    @RequestMapping("/r2")
    public String returnData() {
        return "我是前端返回的数据";
    }

    //
    @ResponseBody
    @RequestMapping("/r3")
    public String returnHTML() {
        return "<h1>我是一级标题</h1>";
    }

    // 返回文本
    @ResponseBody
    @RequestMapping(value = "/r4", produces = "text/plain")
    public String returnText() {
        return "<h1>我是一级标题</h1>";
    }

    //
    @ResponseBody
    @RequestMapping("/r5")
    public UserInfo returnJson() {
        UserInfo userInfo = new UserInfo("zhangsan", 1, 1);
        return userInfo;
    }

    //
    @RequestMapping(value = "/r6")
    public UserInfo setStatue(HttpServletResponse response) {
        response.setStatus(401);
        UserInfo userInfo = new UserInfo("zhangsan", 1, 1);
        return userInfo;
    }

    //
    @RequestMapping(value = "/r7")
    public String setHeader(HttpServletResponse response) {
        response.setHeader("myHeader", "myHeader");
        return "设置 Header 成功";
    }
}
