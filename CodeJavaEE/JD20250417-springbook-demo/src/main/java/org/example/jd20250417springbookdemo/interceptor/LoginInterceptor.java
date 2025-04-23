package org.example.jd20250417springbookdemo.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.example.jd20250417springbookdemo.contant.Constants;
import org.example.jd20250417springbookdemo.model.UserInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-23
 * Time: 18:10
 */
@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle 目标方法执行前~");
        // true 放行，false 拦截
        HttpSession session = request.getSession(false);
        if (!checkUser(session)) {
            // 没有通过验证，执行
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(401);
            String errMsg = "用户未登录";
            response.getOutputStream().write(errMsg.getBytes("UTF-8"));
            return false;
        }
        return true;
        // return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle 目标方法执行后~");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    public boolean checkUser(HttpSession session) {
        if (session == null || session.getAttribute(Constants.SESSION_USER_KEY) == null) {
            log.warn("用户未登录");
            // 用户未登录
            return false;
        }
        UserInfo userInfo = (UserInfo) session.getAttribute(Constants.SESSION_USER_KEY);
        if (userInfo == null || userInfo.getId() <= 0) {
            log.warn("用户未登录");
            return false;
        }
        log.info("用户已登录");
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("视图 view 渲染完成~");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
