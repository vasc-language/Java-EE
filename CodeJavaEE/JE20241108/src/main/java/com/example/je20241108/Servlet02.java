package com.example.je20241108; /**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2024-11-08
 * Time: 20:23
 */

import com.example.je20241108.mode.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Servlet02", value = "/Servlet02")
public class Servlet02 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet02");
        String uname = request.getParameter("uname");
        String password = request.getParameter("password");
        // 开始判断：正确就登录查看学生列表，否则跳转到登陆界面 去重新登陆
        if (password.equals("123456") && uname.equals("uname")) {
            User user = new User(uname, password);
            // 跟踪：
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            // 请求转发：
            request.getRequestDispatcher("StudentList.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
