package org.example;

import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.service.AiServices;
import org.example.assistant.Assistant;
import org.example.assistant.MemoryChatAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-02
 * Time: 10:59
 */
@SpringBootTest
public class ChatMemoryTest {
    @Autowired
    private Assistant assistant;

    /**
     * 测试结果，并没有记忆功能
     */
    @Test
    void testChatMemory() {
        String answer1 = assistant.chat("我是Join2049");
        System.out.println(answer1);

        String answer2 = assistant.chat("我是谁~");
        System.out.println(answer2);
    }

    /**
     * 聊天记忆的简单实现
     */
    @Autowired
    private QwenChatModel qwenChatModel;
    @Test
    void testChatMemory2() {
        //第一轮对话
        UserMessage userMessage1 = UserMessage.userMessage("我是Join2049");
        ChatResponse chatResponse1 = qwenChatModel.chat(userMessage1);
        AiMessage aiMessage1 = chatResponse1.aiMessage();
        //输出大语言模型的回复
        System.out.println(aiMessage1.text());

        //第二轮对话
        UserMessage userMessage2 = UserMessage.userMessage("你知道我是谁吗");
        ChatResponse chatResponse2 = qwenChatModel.chat(Arrays.asList(userMessage1, aiMessage1, userMessage2));
        AiMessage aiMessage2 = chatResponse2.aiMessage();
        //输出大语言模型的回复
        System.out.println(aiMessage2.text());
    }

    /**
     * 使用 ChatMemory 实现聊天记录
     */
    @Test
    void testChatMemory3() {
        // 创建 chatMemory
        MessageWindowChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(20);
        // 创建 AiServices
        Assistant assistant = AiServices
                .builder(Assistant.class)
                .chatLanguageModel(qwenChatModel)
                .chatMemory(chatMemory)
                .build();
        // 调用 service 接口
        String answer1 = assistant.chat("我是Join2049");
        System.out.println(answer1);
        String answer2 = assistant.chat("你知道我是谁吗~");
        System.out.println(answer2);
    }

    /**
     * 使用 AiService 实现聊天功能
     */
    @Autowired
    private MemoryChatAssistant memoryChatAssistant;
    @Test
    void testChatMemory4() {
        String answer1 = memoryChatAssistant.chat("我是Join2049");
        System.out.println(answer1);
        String answer2 = memoryChatAssistant.chat("你知道我是谁吗~");
        System.out.println(answer2);
    }
}
