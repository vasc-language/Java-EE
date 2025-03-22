package com.example.je20241123; /**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2024-11-23
 * Time: 17:56
 */

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletLogin", value = "/ServletLogin")
public class ServletLogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String uname = request.getParameter("uname");
        String sn = request.getParameter("sn");

        JdbcDemo jdbcDemo = new JdbcDemo();
        try {
            Student student = jdbcDemo.JDBCLogin(uname, sn);

            if (student.getSn() == null) {
                request.setAttribute("statement", "登陆失败！");
            } else {
                request.setAttribute("statement", "登陆成功！");
                HttpSession session = request.getSession();
                session.setAttribute("student", student);
            }

            request.getRequestDispatcher("jspLogin.jsp").forward(request, response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
