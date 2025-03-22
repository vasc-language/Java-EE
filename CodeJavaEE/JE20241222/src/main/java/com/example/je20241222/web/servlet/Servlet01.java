package com.example.je20241222.web.servlet; /**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2024-12-22
 * Time: 22:39
 */

import com.example.je20241222.mode.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Servlet01", value = "/Servlet01")
public class Servlet01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 防止中文乱码问题
        request.setCharacterEncoding("utf-8");
        // 1. 获取用户信息(servlet获取前端用户名和密码)
        String uname = request.getParameter("uname");
        String password = request.getParameter("password");
        // 2. 正确就登录查看学生列表，否则跳转到登陆界面 去重新登陆
        if (uname.equals("zhangsan") || password.equals("123456")) {
            User user = new User(uname, password);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            // 正确就 请求转发到展示学生列表
            request.getRequestDispatcher("studentList.jsp").forward(request, response);
        } else {
            // 失败就 请求转发到登陆界面，重新登录
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
