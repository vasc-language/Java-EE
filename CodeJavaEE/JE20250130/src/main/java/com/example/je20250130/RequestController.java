package com.example.je20250130;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-02-01
 * Time: 11:04
 */
@RequestMapping("/request")
@RestController
public class RequestController {
    // 接收一个参数
    @RequestMapping("/r1")
    public String r1(String keyword) {
        return "接收参数：" + keyword;
    }

    // 接收两个及多个参数
    @RequestMapping("/r2")
    public String r2(String userName, String password) {
        return "userName：" + userName + " password：" + password;
    }

    // r3 和 r4 进行对比（包装类和普通类进行比较）：设场景为 number 不返回任何的值
    @RequestMapping("/r3")
    public String r3(Integer number) {
        return "接收参数：" + number; // 接收参数：null
    }

    @RequestMapping("/r4")
    public String r4(int number) {
        return "接收参数：" + number; // "error": "Bad Request"
    }

    @RequestMapping("/r5")
    public String r5(UserInfo userInfo) {
        return "接收参数：userInfo = " + userInfo.toString();
    }

    /**
     * @param keyword
     * @return
     * @RequestMapping 注释
     * 1. 用于在 HPPT 请求中提取查询参数（query parameter）或表单参数，并将其绑定到方法的参数中
     * 2. 它通常处理 GET 请求中的 URL 参数或 POST 请求中的表单数据
     */
    @RequestMapping("/r6")
    public String r6(@RequestParam(value = "q", required = false) String keyword) {
        return "接收参数：" + keyword;
    }

    // 传递数组
    @RequestMapping("/r7")
    public String r7(String[] array) {
        return "接收参数：array=" + Arrays.toString(array);
    }

    /**
     * 传递集合
     * 为什么需要 加上 @RequestParam 注释
     * 1. @RequestParam 告诉 Spring 集合 List 应该从查询参数在中提取数据
     * 2. SpringMVC 的参数绑定参数机制需要明确指令来处理复杂的参数类型（List）。对于查询参数，@RequestParam 是必要的，因为它明确了数据来源。
     * 3. 没有这个注解，Spring无法确定如何处理 List<String> 类型的参数，尤其是当它是通过查询参数传递时。
     *
     * @param list
     * @return
     */

    @RequestMapping("/r8")
    public String r8(@RequestParam List<String> list) {
        return "接收参数：list=" + list;
    }

    /**
     * 传递 Json
     */
    @RequestMapping("/r9")
    public String r9(@RequestBody UserInfo userInfo) {
        return userInfo.toString();
    }

    /**
     * 从 URL 中获取参数
     * 这个 @PathVariable 注释，表示后端期望从 URL 中获取到参数值
     *
     * @param articleId
     * @return
     */
//    @RequestMapping("/article/{articleId}")
//    public String r10(@PathVariable Integer articleId) {
//        return "获取文章 ID：" + articleId;
//    }

    // 将参数名称 type 换成 articleType，而 /article/{type}/{articleId} 后面跟着 两个参数值
    @RequestMapping("/article/{type}/{articleId}")
    public String r11(@PathVariable Integer articleId, @PathVariable("type") Integer articleType) {
        return "articleId: " + articleId + ", articleType: " + articleType;
    }

    // 发送文件
    @RequestMapping("/r12")
    public String r12(@RequestPart("file12") MultipartFile file) throws IOException {
        System.out.println(file.getOriginalFilename());
        // 上传文件
        file.transferTo(new File("D:\\picture\\头像" + file.getOriginalFilename()));
        return "上传文件成功！";
    }

    /**
     * Cookie 和 Session 的联系
     * 1. SessionId 的传递：当用户第一次访问当前网站时，服务器会创建一个 Session ，并生成一个 唯一 SessionID。
     *  这个 SessionID 会存储在 Cookie 中，通过 HTTP 响应发送给用户浏览器。
     * 2. Cookie 携带 SessionID：当用户再次访问网站时，浏览器就会在请求体头自动携带 SessionId 的 Cookie 发送
     *  给服务器。
     * 3. 服务器通过 SessionID 识别用户：用户收到请求，会从 Cookie 中提取 SessionID ，根据这个 SessionID 找
     *  到 Session 的数据，从而识别出用户信息，提取会话。
     *
     * 简单来讲，他们的关系就像：
     * Cookie 像一张通行证：上面记录着用户的 Session ID (通行证号码)。
     * Session 像一个用户档案：存储在服务器上，通过通行证号码 (Session ID) 来索引，记录用户的会话信息。
     * @param request
     * @param response
     * @return
     */
    // 获取 Cookie
    @RequestMapping("/CookieMethod")
    public String CookieMethod(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        StringBuilder builder = new StringBuilder();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                builder.append(cookie.getName() + "：" + cookie.getValue());
            }
        }
        return "返回 Cookie 成功：" + builder;
    }

    // 方式一
    @RequestMapping("/r13")
    public String r13(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName() + "：" + cookie.getValue());
            }
        }
        return "返回 Cookie 成功！";
    }

    // 方式二
    @RequestMapping("/r14")
    public String r14(@CookieValue("java") String java) {
        return "从Cookie中获取Java的值: " + java;
    }

    // 存储 Session
    @RequestMapping("/setSession")
    public String setSession(HttpServletRequest request) {
        // 从 Cookie 中获取 SessionId ，根据 SessionId 获取 Session 对象
        // 假设 SessionId 不存在，则 创建
        HttpSession session = request.getSession();
        session.setAttribute("userName", "zhangsan");
        session.setAttribute("age", 22);
        return "设置 Session 成功";
    }

    //获取session
    // 方式一
    @RequestMapping("/getSession")
    public String getSession(HttpServletRequest request) {
        //从 cookie 中获取 sessionId, 根据 sessionId 获取 Session 对象
        HttpSession session = request.getSession(false);
        //如果用户登录, session 有值, 未登录, session为 null
        if (session == null) {
            return "用户未登录";
        } else {
            //从session中获取登录用户的信息
            String userName = (String) session.getAttribute("userName");
            return "登录用户为: " + userName;
        }
    }

    // 方式二
    @RequestMapping("/getSession2")
    public String getSession2(HttpSession session) {
        String userName = (String) session.getAttribute("userName");
        return "登陆用户为：" + userName;
    }

    // 方式三
    @RequestMapping("/getSession3")
    public String getSession3(@SessionAttribute("userName") String userName) {
        return "登陆用户为：" + userName;
    }

    /**
     * HttpServletRequest 是 Java Servlet API 中代表 HTTP 请求中的接口。当 SpringMVC 接收到 HTTP 请求
     * 并映射到当前方法中，它会自动创建一个 HttpServletRequest 对象。并将请求的所有信息（Header、参数等）封装
     * 到 HttpServletRequest 对象中发送给 getHeader() 方法。
     *
     * 在 SpringMVC 中，如果返回的是 String 类型，并且没有使用 @RequestBody 注释，SpringMVC 默认会将这个字
     * 符串作为视图名称，并尝试渲染对应的视图。但在通常情况下，为了直接返回文本内容，就会加上 @RequestBody 注释。
     * 如果加上 @RequestBody 注释，SpringMVC 就会将字符串作为 HTTP 响应体的内容返回给客户端。
     * @param request
     * @return
     */
    // 方式一
    @RequestMapping("/getHeader1")
    public String getHeader(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        return "从 header 中获取 userAgent：" + userAgent;
    }

    // 方式二
    @RequestMapping("/getHeader2")
    public String getHeader2(@RequestHeader("User-Agent") String userAgent) {
        return "从 header 中获取 userAgent：" + userAgent;
    }
}
