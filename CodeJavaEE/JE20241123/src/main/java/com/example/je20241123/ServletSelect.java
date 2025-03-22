package com.example.je20241123;
/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2024-11-23
 * Time: 16:20
 */

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ServletSelect", value = "/ServletSelect")
public class ServletSelect extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JdbcDemo jdbcDemo = new JdbcDemo();
        try {
            List<Student> students = jdbcDemo.JDBCSelect();
            // 请求转发：
            request.setAttribute("students", students);
            // request.getRequestDispatcher("jspSelect.jsp").forward(request, response);
            request.getRequestDispatcher("jspSelect.jsp").forward(request, response);
            // request.getRequestDispatcher("jspSelect.jsp").forward(request, response);
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
