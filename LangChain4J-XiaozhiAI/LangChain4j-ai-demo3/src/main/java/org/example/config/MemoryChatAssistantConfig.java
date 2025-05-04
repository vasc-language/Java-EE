package org.example.config;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-02
 * Time: 11:29
 */
@Configuration
public class MemoryChatAssistantConfig {
    @Bean
    public ChatMemory chatMemory() {
        // 设置聊天记忆记录的条数 message
        return MessageWindowChatMemory.withMaxMessages(20);
    }
}
