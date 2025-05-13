package org.example.aiagent.MultiTurnConversation;

import dev.langchain4j.community.model.dashscope.QwenChatModel;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-13
 * Time: 21:03
 */
@SpringBootTest
public class ChatClientTest {
    @Resource
    private ChatModel dashscopeChatModel;

    @Test
    void chatClientTest() {
        // 基础用法（ChatModel）
        ChatResponse response = dashscopeChatModel.call(new Prompt("你好"));
        // 高级用法
        ChatClient chatClient = ChatClient.builder(dashscopeChatModel) // 使用构造模式
                .defaultSystem("你是恋爱顾问")
                .build();
        String response2 = chatClient.prompt().user("你好").call().content();

        System.out.println("chatModel: " + response);
        System.out.println("chatClient: " + response2);
    }
}
