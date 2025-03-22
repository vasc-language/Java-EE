package com.example.je20250130.Test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * Description: @RestController 注解：@Controller + @ResponseBody 的完美结合
 * User: 姚东名
 * Date: 2025-02-12
 * Time: 17:04
 */
@RequestMapping("/GreetingRestController")
@RestController
public class GreetingRestController {
    @GetMapping("/greeting") // GreetingRestController/greeting 路径的 GET 请求
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name) {
        return "Hello REST, " + name + "!"; // 直接返回 String 数据，相当于使用了 @ResponseBody
    }

    @GetMapping("/getJsonData")
    public User getJsonData() {
        return new User("lisi", 23); // 直接返回了 User 对象，相当于使用了 ResponseBody
    }
}
