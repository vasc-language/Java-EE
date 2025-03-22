package com.example.demoservlet; /**
 * Created with IntelliJ IDEA.
 * Description: Map集合获取参数值
 * User: 姚东名
 * Date: 2024-10-21
 * Time: 22:21
 */

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "Servlet03", value = "/Servlet03")
public class Servlet03 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * Map<String, String[]> request.getParameterMap()：获取所有参数Map集合
         * String[ ] getParameterValues(String name) ：根据名称获取参数值（数组）
         * String getParameter(String name)：根据名称获取参数值（单个值）
         */
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (String key : parameterMap.keySet()) {
            String[] maps = parameterMap.get(key);
            for (String val : maps) {
                System.out.println(val);
            }
        }

        System.out.println("=============");

        String[] checkboxes = request.getParameterValues("checkbox");
        for (String ch : checkboxes) {
            System.out.println(ch);
        }

        System.out.println("=============");

        String uname = request.getParameter("uname");
        String pwd = request.getParameter("pwd");
        System.out.println("用户名：" + uname);
        System.out.println("密码：" + pwd);

        System.out.println("=============");

        // Object getAttribute(String name)：根据 key，获取值
        String str = (String)request.getAttribute("Attribute");
        System.out.println(str);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
