package org.example.springblogdemo2.common.interceptor;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.springblogdemo2.common.constants.Constants;
import org.example.springblogdemo2.common.util.JwtUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description: 登录拦截器
 * User: 姚东名
 * Date: 2025-05-09
 * Time: 22:06
 */
@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 在登陆之前拦截
     *
     * @param request  current HTTP request
     * @param response current HTTP response
     * @param handler  chosen handler to execute, for type and/or instance evaluation
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求的URI和方法
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        
        log.info("拦截请求：{} {}", method, requestURI);
        
        // 如果是登录请求，直接放行
        if (requestURI.contains("/user/login")) {
            log.info("登录请求，不需要token验证: {} {}", method, requestURI);
            return true;
        }
        
        // 首先从请求头中获取token
        String userToken = request.getHeader(Constants.USER_TOKEN_HEADER_KEY);
        if (userToken != null) {
            log.info("从header中获取token: {}", userToken.substring(0, Math.min(20, userToken.length())) + "...");
        }
        
        // 如果请求头中没有token，尝试从URL参数中获取
        if (userToken == null) {
            userToken = request.getParameter("token");
            if (userToken != null) {
                log.info("从URL参数中获取token: {}", userToken.substring(0, Math.min(20, userToken.length())) + "...");
            }
        }

        if (userToken == null) {
            log.warn("请求头和URL参数中都缺少token，拦截请求并重定向到登录页面: {} {}", method, requestURI);
            redirectToLogin(request, response);
            return false;
        }

        try {
            // 校验 token 是否合法
            Claims claims = JwtUtils.parseToken(userToken);
            if (claims == null) {
                log.warn("Token解析失败，无效的token");
                redirectToLogin(request, response);
                return false;
            }

            // 检查 token  是否过期
            Date expiration = claims.getExpiration();
            if (expiration != null && expiration.before(new Date())) {
                log.warn("Token已过期");
                redirectToLogin(request, response);
                return false;
            }

            // 从 claims 中提取用户信息
            Integer userId = claims.get("userId", Integer.class);
            if (userId == null) {
                log.warn("Token中缺少userId");
                redirectToLogin(request, response);
                return false;
            }
            // 将用户ID放入请求属性中，供后续使用
            request.setAttribute("userId", userId);

            log.info("Token验证成功，用户ID: {}", userId);
            return true;
        } catch (Exception e) {
            log.error("Token验证异常", e);
            redirectToLogin(request, response);
            return false;
        }
    }
    
    /**
     * 重定向到登录页面
     */
    private void redirectToLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取当前请求的URL，便于登录后跳回原页面
        String requestURI = request.getRequestURI();
        String queryString = request.getQueryString();
        String redirectUrl = "/blog_login.html";
        
        // 如果有查询参数，添加到原始请求URL
        if (queryString != null && !queryString.isEmpty()) {
            requestURI += "?" + queryString;
        }
        
        // 将原始请求URL作为参数添加到登录页面URL，进行URL编码
        String encodedReturnUrl = URLEncoder.encode(requestURI, StandardCharsets.UTF_8.toString());
        redirectUrl += "?returnUrl=" + encodedReturnUrl;
        
        log.info("重定向到登录页面: {}", redirectUrl);
        
        // 将请求重定向到登录页面
        response.sendRedirect(redirectUrl);
    }

    /*@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userToken = request.getHeader(Constants.USER_TOKEN_HEADER_KEY);
        log.info("从header中获取token:" + userToken);
        if (userToken == null) {
            // 拦截
            response.setStatus(401);
            return false;
        }
        // 校验 token 是否合法
        Claims claims = JwtUtils.parseToken(userToken);
        if (claims == null) {
            // 拦截
            response.setStatus(401);
            return false;
        }
        return true;
    }
*/
}
