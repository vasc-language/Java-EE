package org.study.langchain4jaidemo.controller;


import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;

import java.util.List;
import java.util.ArrayList;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.study.langchain4jaidemo.service.Assistant;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-27
 * Time: 22:17
 */
@Slf4j
@RequestMapping("/ChatLanguageModelController")
@RestController
public class ChatLanguageModelController {
    @Resource(name = "chatLanguageModel")
    private ChatLanguageModel chatLanguageModel;
    @Resource
    private Assistant assistant;


    // 第一个AI 启动程序 调用 deepseek-chat
    @GetMapping("/hello")
    public String hello(@RequestParam(value = "userMessage", defaultValue = "你是谁？") String userMessage) {
        String result = chatLanguageModel.chat(userMessage);
        System.out.println("通过LangChain4j 调用 deepseek-chat 模型返回结果：" + result);
        return result;
    }

    @GetMapping("/lowApi")
    public String lowApi(@RequestParam("userMessage") String userMessage) {
        List<ChatMessage> messages = new ArrayList<>();
        messages.add(UserMessage.from(userMessage));
        // 直接使用单个消息的方式调用chat方法
        String response = chatLanguageModel.chat(userMessage);
        return response;
    }

    @GetMapping("/highApi")
    public String highApi(@RequestParam(value = "userMessage", defaultValue = "你是谁") String userMessage) {
        return assistant.chat(userMessage);
    }
}
