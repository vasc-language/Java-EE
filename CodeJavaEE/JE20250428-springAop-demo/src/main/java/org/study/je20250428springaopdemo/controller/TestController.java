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
 * Time: 7:34
 */
@Slf4j
@RequestMapping("/test")
@RestController
public class TestController {
    // Integer Boolean String
    @RequestMapping("/t1")
    public Integer t1() {
        int a = 10 / 0;
        log.info("执行t1");
        return 1;
    }
    @MyAspect
    @RequestMapping("/t2")
    public Boolean t2() {
        log.info("执行t1");
        return true;
    }
    @RequestMapping("/t3")
    public String t3() {
        log.info("执行t1");
        return "t3";
    }
}
