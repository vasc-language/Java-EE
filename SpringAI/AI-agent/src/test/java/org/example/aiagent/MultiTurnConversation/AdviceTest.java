package org.example.aiagent.MultiTurnConversation;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created with IntelliJ IDEA.
 * Description: 拦截器（顾问）
 * User: 姚东名
 * Date: 2025-05-14
 * Time: 7:24
 */
@SpringBootTest
public class AdviceTest {
    @Autowired
    private ChatClient chatClient;
}
