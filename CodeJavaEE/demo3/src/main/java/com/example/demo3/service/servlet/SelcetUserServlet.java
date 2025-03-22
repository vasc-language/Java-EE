package com.example.demo3.service.servlet; /**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2024-12-03
 * Time: 16:01
 */

import com.example.demo3.mode.User;
import com.example.demo3.service02.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "SelcetUserServlet", value = "/SelcetUserServlet")
public class SelcetUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserService();
        List<User> users = userService.selectUserList();
        request.setAttribute("users", users);
        // 请求转发：
        request.getRequestDispatcher("UserList.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
