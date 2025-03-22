package com.example.je20241031; /**
 * Created with IntelliJ IDEA.
 * Description: 获取浏览器发送的Cookie
 * User: 姚东名
 * Date: 2024-10-31
 * Time: 17:50
 */

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.URLDecoder;

@WebServlet(name = "Servlet02", value = "/Servlet02")
public class Servlet02 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取浏览器发送的Cookie
        Cookie[] cookies = request.getCookies();
        String uname = null;
        //开始遍历cookie数组的value
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("uname")) {
                uname = cookie.getValue();
                uname = URLDecoder.decode(uname, "utf-8");
            }
        }
        /**
         * 中间部分：
         * 根据不同的老师 去数据库中查询到相应的班级ID
         * 根据查询到的班级ID 查询班级学生列表（list集合）
         * 把查询到的集合放到域中，方便前台显示
         */

        //请求转发：
        request.setAttribute("uname", uname);
        request.getRequestDispatcher("jsp03.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
