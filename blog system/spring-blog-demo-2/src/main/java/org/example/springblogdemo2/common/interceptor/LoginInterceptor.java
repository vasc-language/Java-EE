package org.example.springblogdemo2.common.interceptor;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.springblogdemo2.common.constants.Constants;
import org.example.springblogdemo2.common.util.JwtUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

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
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userToken = request.getHeader(Constants.USER_TOKEN_HEADER_KEY);
        log.info("从header中获取token: {}", userToken);

        if (userToken == null) {
            log.warn("请求头中缺少token，拦截请求");
            response.setStatus(401);
            return false;
        }

        try {
            // 校验 token 是否合法
            Claims claims = JwtUtils.parseToken(userToken);
            if (claims == null) {
                log.warn("Token解析失败，无效的token");
                response.setStatus(401);
                return false;
            }

            // 检查 token  是否过期
            Date expiration = claims.getExpiration();
            if (expiration != null && expiration.before(new Date())) {
                log.warn("Token已过期");
                response.setStatus(401);
                return false;
            }

            // 从 claims 中提取用户信息
            Long userId = Long.valueOf(claims.getSubject());
            // 将用户ID放入请求属性中，供后续使用
            request.setAttribute("userId", userId);

            log.info("Token验证成功，用户ID: {}", userId);
            return true;
        } catch (Exception e) {
            log.error("Token验证异常", e);
            response.setStatus(401);
            return false;
        }
    }
}
