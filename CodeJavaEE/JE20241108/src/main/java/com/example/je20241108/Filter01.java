package com.example.je20241108; /**
 * Created with IntelliJ IDEA.
 * Description: Filter拦截所有
 * User: 姚东名
 * Date: 2024-11-08
 * Time: 20:48
 */

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Filter 可以根据需求，配置不同的拦截资源路径
 * 1）拦截具体的资源：/index.jsp：只有访问index.jsp时才会被拦截
 * 2）目录拦截：/user/*：访问/user下所有的资源，都会被拦截
 * 3）后缀名拦截：*.jsp：访问后缀名为jsp的资源，才会被拦截
 * 4）拦截所有：/*：访问所有资源，都会被拦截
 */
// 开始拦截（过滤）：value = "/*"表示拦截所有信息
@WebFilter(filterName = "Filter01", value = "/*")
public class Filter01 implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        // 每次请求都需要经过Filter01（进行过滤）才能访问服务器里面的东西（Servlet、JSP、HTML等Web资源）
        request.setCharacterEncoding("utf-8");
        HttpServletRequest req = (HttpServletRequest) request;
        String[] urls = {"/index.jsp", "/Servlet02", "/Serlet01"};
        // StringBuffer requestURL = req.getRequestURL(); 这个类型是StringBuffer，需要变成String
        String requestURL = req.getRequestURI().toString();

        // 让urls数组中的元素不用进行过滤，直接放行
        for (String url : urls) {
            if (requestURL.contains(url)) {
                chain.doFilter(request, response);
                return;
            }
        }
        // 拦截没有正确输入用户名信息的动作，跳转到登陆界面
        HttpSession session = req.getSession();
        if (session.getAttribute("user") == null) {
            request.getRequestDispatcher("login.jsp").forward(req, response);
        }

        System.out.println("我是一个filter1");
        // 放行：让其访问本该访问的资源
        chain.doFilter(request, response);
        System.out.println("我是一个filter2");
    }
}
