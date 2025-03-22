package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2024-11-27
 * Time: 20:42
 */
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello() {
        return "hello Spring boot~";
    }
}
