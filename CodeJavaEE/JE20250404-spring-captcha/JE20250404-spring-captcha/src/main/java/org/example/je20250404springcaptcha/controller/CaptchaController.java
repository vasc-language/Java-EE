package org.example.je20250404springcaptcha.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.je20250404springcaptcha.model.CaptchaProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-04
 * Time: 10:03
 */
@RequestMapping("/captcha")
@RestController
public class CaptchaController {
    // 在程序中或获取日志需要使用日志工厂 LoggerFactory
    private static final Logger logger = LoggerFactory.getLogger(CaptchaController.class);
    // 将配置文件的数据取出来
    @Autowired
    private CaptchaProperties captchaProperties;
    // 验证时间不超过30分钟
    private final static Long VALID_TIME = 30 * 60 * 1000L;

    // 生成验证码
    @RequestMapping("/getCaptcha")
    public void getCaptcha(HttpSession session, HttpServletResponse response) {
        // 开始时间
        long start = System.currentTimeMillis();
        response.setContentType("image/jpeg");
        response.setCharacterEncoding("utf-8");
        // response.setHeader();

        try {
            // 利用 huTool的API生成 验证的图片
            ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(captchaProperties.getWidth(), captchaProperties.getHeight());
            String code = captcha.getCode();
            // 利用 Session 存储 该验证码的 key 和 date
            session.setAttribute(captchaProperties.getSession().getKey(), code);
            session.setAttribute(captchaProperties.getSession().getDate(), new Date());

            captcha.write(response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // 结束时间
        long end = System.currentTimeMillis();
        logger.info("getCaptcha cost time: " + (end - start));
    }

    /**
     * 验证用户输入的验证码是否正确
     * @param captcha 用户输入的参数(验证码)
     * @param session
     * return 成功返回 true 失败返回 false
     */
    @RequestMapping("check")
    public boolean check(String captcha, HttpSession session) {
        if (!StringUtils.hasLength(captcha)) {
            return false;
        }
        // 从 session 中取出 验证码的 key 和 date
        String code = (String) session.getAttribute(captchaProperties.getSession().getKey());
        Date date = (Date) session.getAttribute(captchaProperties.getSession().getDate());

        if (captcha.equalsIgnoreCase(code) && date != null
            && System.currentTimeMillis() - date.getTime() <= VALID_TIME) {
            return true;
        }
        return false;
    }
}
