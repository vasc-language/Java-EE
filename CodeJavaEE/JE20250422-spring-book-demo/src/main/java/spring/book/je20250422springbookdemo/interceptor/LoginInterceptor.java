package spring.book.je20250422springbookdemo.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import spring.book.je20250422springbookdemo.contant.Contants;
import spring.book.je20250422springbookdemo.model.UserInfo;

/**
 * Created with IntelliJ IDEA.
 * Description: 定义拦截器
 * User: 姚东名
 * Date: 2025-04-23
 * Time: 18:59
 */
@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle 目标方法之前~");
        /**
         * session 是从 request 中拿出来的 在 login 方法中
         * session.setAttribute(Constants.SESSION_USER_KEY)
         * 那么在拦截器中通过 HttpSession session = request.getSession(false); 拿到 userInfo 对象中的所有属性
         */
        HttpSession session = request.getSession(false);
        /**
         * if() 语句的作用
         * 这段代码并不是在做二次校验，它的作用是：
         * 当 checkUser(session) 返回 false （即用户 未登录或登录信息非法）时，立即：
         * 1. 设置响应的 Content-Type 为 text/html;charset=utf-8
         * 2. 将 HTTP 状态码设为 401 （Unauthorized）
         * 3. 在响应体中里写入一段错误提示（"用户未登录"）
         * 4. return false, 让拦截器链和后续的 Controller 方法不再执行，直接把这个 401 响应回给客户端
         */
        if (!checkUser(session)) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(401);
            String errMsg = "用户未登录";
            response.getOutputStream().write(errMsg.getBytes("utf-8"));
            return false;
        }
        return true;
        // return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle 目标方法之后~");
        // HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * checkUser() 本身只是拿到 session 后做了一些业务校验，返回一个布尔值
     * 不会主动往客户端写任何东西，更不会中断请求
     * @param session
     * @return
     */
    public boolean checkUser(HttpSession session) {
        if (session == null || session.getAttribute(Contants.SESSION_USER_KEY) == null) {
            log.warn("用户未登录");
            return false;
        }

        UserInfo userInfo = (UserInfo) session.getAttribute(Contants.SESSION_USER_KEY);
        // 继续验证
        if (userInfo == null || userInfo.getId() <= 0) {
            log.warn("用户未登录");
            return false;
        }

        log.info("用户登陆成功！");
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("加载视图完成拦截~");
        // HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
