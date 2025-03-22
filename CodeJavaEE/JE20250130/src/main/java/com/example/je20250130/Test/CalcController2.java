package com.example.je20250130.Test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-02-13
 * Time: 23:00
 */
@RequestMapping("/calc")
@Controller
public class CalcController2 {
    @RequestMapping("/calculate")
    @ResponseBody // 统一处理所有计算结果
    public String calculate(
            @RequestParam("operation") String operation, // 选择要运算的法则
            @RequestParam("num1") Integer num1,
            @RequestParam("num2") Integer num2
    ) {
        if (num1 == null || num2 == null || operation == null || operation.isEmpty()) {
            return "参数不合法，请重新输入";
        }

        String resultHtml = null;

        switch (operation) {
            case "sum":
                Integer sum = num1 + num2;
                resultHtml = "<h1>计算机计算结果(加法)：" + sum + "</h1>";
                break;
            case "sub":
                Integer sub = num1 - num2;
                resultHtml = "<h1>计算机计算结果(减法)：" + sub + "</h1>";
                break;
            case "mul":
                Integer mul = num1 * num2;
                resultHtml = "<h1>计算机计算结果(乘法)：" + mul + "</h1>";
                break;
            case "div":
                if (num2 == 0) {
                    return "<h1>除数不能为零！</h1>";
                }
                double divResult = (double) num1 / num2;
                resultHtml = "<h1>计算机计算结果(除法)：" + divResult + "</h1>";
                break;
            default:
                return "<h1>不支持的运算类型</h1>";
        }
        return resultHtml;
    }
}
