package com.example.demoservlet; /**
 * Created with IntelliJ IDEA.
 * Description: Response 完成重定向
 * User: 姚东名
 * Date: 2024-10-22
 * Time: 17:46
 */

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Servlet06", value = "/Servlet06")
public class Servlet06 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8"); //设置字符输入流的编码（post）
        String uname = request.getParameter("uname");
        String pwd = request.getParameter("pwd");
        System.out.println(uname);
        System.out.println(pwd);
        /**
         * 1. 设置响应状态码 302
         * 2. 设置响应头
         * 3. 是否添加虚拟目录
         *  浏览器使用：需要加虚拟目录（项目访问路径）-> 用户看到的内容
         *  服务器使用：不需要加虚拟目录 -> 字节流 字符流
         */

        /*// 1. 服务器使用：
        response.setStatus(302);
        response.setHeader("location", "/Servlet06");

        System.out.println("====================");

        // 2. 浏览器使用：简化版本
        response.sendRedirect("https://www.baidu.com/");
        response.setContentType("text/html; charset=UTF-8");*/

        // 通过Response对象获取字符输出流
        PrintWriter writer = response.getWriter(); //获取字符输出流
        writer.write("<h1>write</h1>");
        writer.write("<h1>write</h1>");
        writer.write("<h1>write</h1>");
        writer.write("<h1>write</h1>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
