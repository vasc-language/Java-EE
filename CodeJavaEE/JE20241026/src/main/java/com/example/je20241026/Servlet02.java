package com.example.je20241026;
/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2024-10-25
 * Time: 20:34
 */

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Servlet02", value = "/Servlet02")
public class Servlet02 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //保证编码正确，不会出现乱码
        request.setCharacterEncoding("utf-8");
        String uname = request.getParameter("uname");
        String pwd = request.getParameter("pwd");
        if (pwd.equals("123456")) {
            //请求转发：
            request.setAttribute("uname", uname);
            request.getRequestDispatcher("/JSP02.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
