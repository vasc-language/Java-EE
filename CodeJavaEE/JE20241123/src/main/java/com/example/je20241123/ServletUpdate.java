package com.example.je20241123;
/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2024-11-23
 * Time: 15:33
 */

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletUpdate", value = "/ServletUpdate")
public class ServletUpdate extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JdbcDemo jdbcDemo = new JdbcDemo();
        try {
            jdbcDemo.JDBCUpdate();
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
