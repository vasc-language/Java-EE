package org.study.je20250428springaopdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.study.je20250428springaopdemo.aspect.MyAspect;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-28
 * Time: 7:32
 */
@Slf4j
@RequestMapping("/user")
@RestController
public class UserInfoController {
    @RequestMapping("/u1")
    public String u1() {
        log.info("执行u1");
        return "u1";
    }
    @MyAspect
    @RequestMapping("/u2")
    public String u2() {
        log.info("执行u2");
        return "u2";
    }
}
