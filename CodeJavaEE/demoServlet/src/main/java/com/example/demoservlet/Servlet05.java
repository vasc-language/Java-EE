package com.example.demoservlet;
/**
 * Created with IntelliJ IDEA.
 * Description: Request 请求参数中文乱码问题
 * User: 姚东名
 * Date: 2024-10-22
 * Time: 16:49
 */

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * tomcat8的编码是：utf-8
 * tomcat7的编码是：ISO8859-1
 * tomcat10的编码是：ISO8859-1
 * get  浏览器请求行utf-8 -> tomcat -> 解码 -> request(字符串) -> 获取的字符串
 * post 浏览器请求行utf-8 -> 流 -> tomcat -> 流 -> request 使用流的形式获取 ISO8859-1（utf-8） -> 乱码
 */
@WebServlet(name = "Servlet05", value = "/Servlet05")
public class Servlet05 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 解决方案：
         * POST：设置输入流的编码
         * req.setCharacterEncoding(“UTF-8");
         * 通用方式（GET/POST）：先编码，再解码
         * new String(username.getBytes("ISO-8859-1"),"UTF-8");
         */
        request.setCharacterEncoding("utf-8"); //设置字符输入流的编码 -- post
        String uname = request.getParameter("uname");
        String pwd = request.getParameter("pwd");
        System.out.println(uname);
        System.out.println(pwd);

        System.out.println("================");

        //get的乱码解决（tomcat7）
        //uname = new String(uname.getBytes(StandardCharsets.UTF_8));
        uname = new String(uname.getBytes("ISO-8859-1"),"UTF-8");
        /**
         * 请求转发资源间共享数据：使用request对象
         * void setAttribute(String name, Object o)：存储数据到 request域中
         * Object getAttribute(String name)：根据 key，获取值
         */
        request.setAttribute("Attribute", "String");
        request.getRequestDispatcher("/Servlet03").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
