package com.example.je20241108; /**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2024-11-08
 * Time: 20:31
 */

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Servlet03", value = "/Servlet03")
public class Servlet03 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet03");
        request.getRequestDispatcher("JSP01.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
