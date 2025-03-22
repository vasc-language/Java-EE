package com.example.je20250130.Test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created with IntelliJ IDEA.
 * Description: 默认会进行视图解析，查找和渲染视图
 * User: 姚东名
 * Date: 2025-02-12
 * Time: 17:02
 */
@RequestMapping("/GreetingController")
@Controller // 控制器
public class GreetingController {
    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        // 将数据添加到 Model，传递给视图
        model.addAttribute("name", name);
        // 返回视图名称(例如 greeting.jsp 或 greeting.html)
        return "greeting";
    }
}
