package com.example.demoservlet;
/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2024-10-21
 * Time: 21:55
 */

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Servlet02", value = "/Servlet02")
public class Servlet02 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 获取请求行数据
         * get请求参数 -> 获取字符串
         * 获取请求头
         * post获取请求体 -> 字符流
         */
        request.setCharacterEncoding("utf-8"); //设置字符输入流的编码 -- post
        String uname = request.getParameter("uname");
        String pwd = request.getParameter("pwd");
        System.out.println(uname);
        System.out.println(pwd);

        // 通过Response对象获取字符输出流
        PrintWriter writer = response.getWriter(); //获取字符输出流
        writer.write("<h1>write</h1>");
        writer.write("<h1>write</h1>");
        writer.write("<h1>write</h1>");
        writer.write("<h1>write</h1>");
    }

    //Request 通用方式获取请求参数
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
