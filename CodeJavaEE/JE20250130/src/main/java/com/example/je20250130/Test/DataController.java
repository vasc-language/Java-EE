package com.example.je20250130.Test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * Description: 绕过视图解析
 * User: 姚东名
 * Date: 2025-02-12
 * Time: 17:03
 */
@Controller
@RequestMapping("/DataController")
public class DataController {
    @GetMapping("/Data")
    @ResponseBody
    public String Data(@RequestParam(name = "name", required = false, defaultValue = "world") String name) {
        return "hello " + name + "!"; // 直接返回 一个 String
    }

    @GetMapping("/json")
    @ResponseBody
    public User getJsonData() {
        return new User("zhangsan", 22); // 直接返回 User 对象
    }
}
