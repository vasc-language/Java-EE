package org.example.tools;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import dev.langchain4j.agent.tool.ToolMemoryId;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * Description: Function calling 函数调用 (Tools 工具)
 * User: 姚东名
 * Date: 2025-05-03
 * Time: 17:34
 */
@Component
public class CalculatorTools {
    /*@Tool
    double sum(double a, double b) {
        System.out.println("调用加法运算");
        return a + b;
    }

    @Tool
    double squareRoot(double x) {
        System.out.println("调用平方根运算");
        return Math.sqrt(x);
    }*/

    // 在 SeparateChatAssistant 中添加 tools 属性配置
    @Tool(name = "加法运算", value = "将两个参数 a 和 b 相加并返回运算结果")
    double sum(
            @ToolMemoryId int memoryId,
            @P(value = "加数1", required = true) double a,
            @P(value = "加数2", required = true) double b
    ) {
        System.out.println("调用加法运算 memoryId:" + memoryId);
        return a + b;
    }

    @Tool(name = "平方根运算", value = "计算给定参数 x 的平方根并返回结果")
    double squareRoot(@ToolMemoryId int memoryId, double x) {
        System.out.println("调用平方根运算 memoryId:" + memoryId);
        return Math.sqrt(x);
    }
}
