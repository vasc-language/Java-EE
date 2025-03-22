/**
 * Created with IntelliJ IDEA.
 * Description: 获取请求数据
 * User: 姚东名
 * Date: 2024-10-22
 * Time: 11:55
 */

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Servlet04", value = "/Servlet04")
public class Servlet04 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 请求行：
        String method = request.getMethod(); //获取请求方式： POST
        System.out.println("请求方式：" + method);
        String contextPath = request.getContextPath(); //获取虚拟目录：/demoServlet_war_exploded
        System.out.println("虚拟目录：" + contextPath);
        StringBuffer requestURL = request.getRequestURL();
        //获取URL（统一资源定位符）：定位符)：http://localhost:8080/demoServlet_war_exploded/Servlet04
        System.out.println("URL(统一资源定位符)：" + requestURL);
        String requestURI = request.getRequestURI(); //获取URI(统一资源标识符)：/demoServlet_war_exploded/Servlet04
        System.out.println("URL(统一资源标识符)：" + requestURI);
        String queryString = request.getQueryString(); //获取请求参数：null
        System.out.println("请求参数：" + queryString);

        System.out.println("======================");

        // 2. 请求头
        String header = request.getHeader("user-agent"); //获取请求头信息 -->浏览器版本user-agent
        System.out.println(header);
        //Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0

        System.out.println("======================");

        // 3. 请求体
        BufferedReader reader = request.getReader(); //获取字符流
        String s = reader.readLine();
        System.out.println(s); //uname=servlet&pwd=212409&checkbox=2
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
