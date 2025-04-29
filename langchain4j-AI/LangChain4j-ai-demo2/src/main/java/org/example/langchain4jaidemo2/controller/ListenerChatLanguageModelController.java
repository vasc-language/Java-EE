package org.example.langchain4jaidemo2.controller;

import dev.langchain4j.model.chat.ChatLanguageModel;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-29
 * Time: 22:38
 */
@Slf4j
@RequestMapping("/ListenerChatLanguageModelController")
@RestController
public class ListenerChatLanguageModelController {
    @Resource(name = "chatLanguageModel2")
    private ChatLanguageModel chatLanguageModel;

    @GetMapping("/helloConfig")
    public void hello(String userMessage) {
        // String result = chatLanguageModel.generate(userMessage);

        System.out.println("通过");
    }
}
