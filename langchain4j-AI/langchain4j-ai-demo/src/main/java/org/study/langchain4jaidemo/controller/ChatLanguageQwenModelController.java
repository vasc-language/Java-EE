package org.study.langchain4jaidemo.controller;

import dev.langchain4j.model.chat.ChatLanguageModel;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-29
 * Time: 19:47
 */
@Slf4j
@RequestMapping("/ChatLanguageQwenModelController")
@RestController
public class ChatLanguageQwenModelController {
    @Resource(name = "chatLanguageModel2")
    private ChatLanguageModel chatLanguageModel;

    // 测试 config 类 调用 qwen-turbo
    @GetMapping("/helloConfig")
    public String helloConfig(@RequestParam(value = "userMessage", defaultValue = "你是谁？") String userMessage) {
        String result = chatLanguageModel.chat(userMessage);
        System.out.println("通过LangChain4j 调用 qwen-turbo 模型返回结果：" + result);
        return result;
    }
}
