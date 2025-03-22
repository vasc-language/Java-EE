package com.example.je20250130;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * Description: 需求: 输⼊两个整数, 点击"点击相加"按钮, 显⽰计算结果
 * User: 姚东名
 * Date: 2025-02-12
 * Time: 22:29
 */
@RequestMapping("/calc")
// @RestController 返回数据 Controller 返回视图
@RestController
public class CalcController {
    @RequestMapping("/sum")
    public String sum(Integer num1, Integer num2) {
        if (num1 == null || num2 == null) {
            return "参数不合法，请重新输入";
        }
        Integer sum = num1 + num2;
        return "<h1>计算机计算结果：" + sum + "</h1>";
    }
}
