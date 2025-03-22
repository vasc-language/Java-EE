package com.example.je20241031; /**
 * Created with IntelliJ IDEA.
 * Description: Cookie的基本使用
 * User: 姚东名
 * Date: 2024-10-31
 * Time: 17:28
 */

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;

@WebServlet(name = "Servlet01", value = "/Servlet01")
public class Servlet01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置字符编码集
        request.setCharacterEncoding("utf-8");
        //获取页面的元素：uname、password
        String uname = request.getParameter("uname");
        String password = request.getParameter("password");

        if (password.equals("123456")) {
            /**
             * Cookie 不能直接存储中文
             * 如需要存储，则需要进行转码：URL编码
             */
            // URL编码
            String encode = URLEncoder.encode(uname, "utf-8");

            // 创建一个Cookie对象: Cookie cookie = new Cookie("key","value");
            Cookie cookie = new Cookie("uname", encode);

            /**
             * Cookie 存活时间
             * 默认情况下，Cookie 存储在浏览器内存中，当浏览器关闭，内存释放，则Cookie被销毁
             * setMaxAge(int seconds)：设置Cookie存活时间
             * 正数：将 Cookie写入浏览器所在电脑的硬盘，持久化存储。到时间自动删除
             * 负数：默认值，Cookie在当前浏览器内存中，当浏览器关闭，则 Cookie被销毁
             * 零：删除对应 Cookie
             */
            cookie.setMaxAge(60 * 2);

            // 把Cookie对象响应给浏览器
            response.addCookie(cookie);
            System.out.println(cookie);

            //请求转发
            request.setAttribute("uname", uname);
            request.getRequestDispatcher("jsp01.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
