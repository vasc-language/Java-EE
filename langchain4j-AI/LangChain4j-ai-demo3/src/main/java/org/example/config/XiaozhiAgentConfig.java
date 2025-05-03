package org.example.config;

import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import org.example.store.MongoChatMemoryStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 * Description: 配置小智Agent的持久化和记忆隔离
 * User: 姚东名
 * Date: 2025-05-03
 * Time: 16:57
 */
@Configuration
public class XiaozhiAgentConfig {
    // 持久化
    @Autowired
    private MongoChatMemoryStore mongoChatMemoryStore;

    /**
     * 记忆隔离
     * @return
     */
    @Bean
    ChatMemoryProvider chatMemoryProviderXiaozhi() {
        return memoryId -> MessageWindowChatMemory.builder().
                id(memoryId)
                .chatMemoryStore(mongoChatMemoryStore) // 配置持久化
                .maxMessages(100)
                .build();
    }
}
