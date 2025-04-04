/*
package org.example.je20250402springcaptcha.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.je20250402springcaptcha.configuration.CaptchaProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Date;


*/
/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-02
 * Time: 21:48
 *//*

@RequestMapping("captcha")
@RestController
public class CaptchaController {
    private static final Logger logger = LoggerFactory.getLogger(CaptchaController.class);
    @Autowired
    private CaptchaProperties captchaProperties;
    private final static Long VALID_TIME = 30 * 60 * 1000l; // 半分钟

    // 生成验证码
    @RequestMapping("/getCaptcha")
    public void getCaptcha(HttpSession session, HttpServletResponse response) {
        long start = System.currentTimeMillis();
        response.setContentType("image/jpeg");
        //一般情况下, 这个处理是前端做的
        response.setHeader("Progma", "No-cahce");
        // 处理乱码问题
        // response.setCharacterEncoding("utf-8");
        // 生成验证码
        try {
            //定义图形验证码的长、宽、验证码字符数、干扰线宽度
            ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(captchaProperties.getWidth(), captchaProperties.getHeight());
            String code = captcha.getCode();
            // 将生成的验证码和验证时间保存到 session 中
            session.setAttribute(captchaProperties.getSession().getKey(), code);
            session.setAttribute(captchaProperties.getSession().getDate(), new Date());

            captcha.write(response.getOutputStream());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        long end = System.currentTimeMillis();
        logger.info("getCaptcha cost time: " + (end - start) + "ms");
    }

    */
/**
     * 验证用户输入的验证码是否正确
     * @param session 从 session 中拿 code 和 date
     * @param captcha 用户输入的验证码
     * @return
     *//*

    @RequestMapping("/check")
    public boolean check(HttpSession session, String captcha) {
        if (!StringUtils.hasLength(captcha)) {
            return false;
        }
        // 验证 code 和 date
        String code = (String) session.getAttribute(captchaProperties.getSession().getKey());

        Date date = (Date) session.getAttribute(captchaProperties.getSession().getDate());
        // System.out.println(code);
        // //验证码正确, 且没有过期(在半分钟内), 具体逻辑, 需要看需求
        // equalsIgnoreCase 不区分大小写
        if (captcha.equalsIgnoreCase(code) && date != null
                && System.currentTimeMillis() - date.getTime() <= VALID_TIME) {
            return true;
        }
        return false;
    }
}
*/
// package com.bit.captcha.controller;
package org.example.je20250402springcaptcha.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
// import com.bit.captcha.model.CaptchaProperties;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.je20250402springcaptcha.configuration.CaptchaProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;

@RequestMapping("/captcha")
@RestController
public class CaptchaController {

    private static final Logger logger = LoggerFactory.getLogger(CaptchaController.class);
    @Autowired
    private CaptchaProperties captchaProperties;
    private final static Long VALID_TIME = 30 * 60 * 1000L;


    @RequestMapping("/getCaptcha")
    public void getCaptcha(HttpSession session, HttpServletResponse response){
        long start = System.currentTimeMillis();
        response.setContentType("image/jpeg");
        //一般情况下, 这个处理是前端做的
        response.setHeader("Progma", "No-cahce");
//        response.setCharacterEncoding("utf-8");
        //生成验证码
        try {
            ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(captchaProperties.getWidth(), captchaProperties.getHeight());
            String code = captcha.getCode();
            session.setAttribute(captchaProperties.getSession().getKey(), code);
            session.setAttribute(captchaProperties.getSession().getDate(), new Date());

            captcha.write(response.getOutputStream());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        long end = System.currentTimeMillis();
//        System.out.println("getCaptcha cost time: "+ (end - start) + "ms");
        logger.info("getCaptcha cost time: "+ (end - start) + "ms");
    }

    /**
     * 验证用户输入的验证码是否正确
     * @param captcha: 用户输入的验证码
     * @param session
     * @return false: 校验失败, true: 成功
     */
    @RequestMapping("/check")
    public boolean check(String captcha, HttpSession session){
        if (!StringUtils.hasLength(captcha)){
            return false;
        }
        //验证 验证码
        String code = (String)session.getAttribute(captchaProperties.getSession().getKey());
        Date date = (Date)session.getAttribute(captchaProperties.getSession().getDate());
        //验证码正确, 且没有过期, 具体逻辑, 需要看需求
        if (captcha.equalsIgnoreCase(code) && date!=null
                && System.currentTimeMillis()-date.getTime()<VALID_TIME){
            return true;
        }
        return false;
    }
}
