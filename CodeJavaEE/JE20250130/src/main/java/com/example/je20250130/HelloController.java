package com.example.je20250130;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-02-01
 * Time: 9:55
 */
@RequestMapping("/hello")
@RestController
public class HelloController {
    @RequestMapping("/r1")
    public String hello() {
        return "hello SpringBoot~";
    }
}
