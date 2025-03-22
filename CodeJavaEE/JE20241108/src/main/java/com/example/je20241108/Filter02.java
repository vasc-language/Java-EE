package com.example.je20241108; /**
 * Created with IntelliJ IDEA.
 * Description:过滤器链：Filter01 -> Filter02
 * User: 姚东名
 * Date: 2024-11-08
 * Time: 21:10
 */

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * 过滤器链:
 * 一个Web应用，可以配置多个过滤器，这多个过滤器称为过滤器链
 * 注解配置的Filter，优先级按照过滤器类名(字符串)的自然排序
 */
// 开始拦截（过滤）：value = "/Servlet03"表示只拦截Servlet03中的信息
@WebFilter(filterName = "Filter02", value = "/Servlet03")
public class Filter02 implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println("接下来去Servlet03");
        chain.doFilter(request, response);
        System.out.println("刚从Servlet03中出来");
    }
}
