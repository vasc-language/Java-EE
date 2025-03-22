package com.example.je20241031; /**
 * Created with IntelliJ IDEA.
 * Description: Session的获取和它的对象功能
 * User: 姚东名
 * Date: 2024-11-02
 * Time: 10:38
 */

import com.example.je20241031.mode.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * Session对象功能：
 * void setAttribute(String name, Object o)：存储数据到 session 域中
 * Object getAttribute(String name)：根据 key，获取值
 *  void removeAttribute(String name)：根据 key，删除该键值对
 */
@WebServlet(name = "Servlet04", value = "/Servlet04")
public class Servlet04 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置字符集
        request.setCharacterEncoding("utf-8");
        String uId = request.getParameter("uId");
        String classId = request.getParameter("classId");
        String uName = request.getParameter("uName");
        String password = request.getParameter("password");

        //开始登录：
        if (password.equals("123456")) {
            User user = new User(uName, uId, password, classId);

            //获取Session对象：
            HttpSession session = request.getSession();

            //向Session对象中，添加内容：
            session.setAttribute("user", user);
        }

        //请求转发
        request.getRequestDispatcher("jsp04.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
