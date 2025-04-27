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
 * Date: 2025-04-27
 * Time: 21:17
 */
@Slf4j
@RestController
@RequestMapping("HelloLangChain4jController")
public class HelloLangChain4jController {
    @Resource
    private ChatLanguageModel chatLanguageModel;
    @GetMapping("/hello")
    public String hello(@RequestParam(value = "prompt", defaultValue = "你是谁") String prompt) {
        String result = chatLanguageModel.chat(prompt);

        System.out.println("通过 LangChain4j 调用 deepseek-chat 模型返回结果：\n" + result);

        return result;
    }
}
