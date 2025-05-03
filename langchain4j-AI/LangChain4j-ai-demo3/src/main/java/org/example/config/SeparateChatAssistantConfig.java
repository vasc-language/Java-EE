package org.example.config;

import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import org.example.store.MongoChatMemoryStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-03
 * Time: 9:00
 */
@Configuration
public class SeparateChatAssistantConfig {
    @Autowired
    private MongoChatMemoryStore mongoChatMemoryStore;

    /**
     * 与普通接口的区别
     * 1. 方法数量限制
     *   - 普通接口可以有任意数量的抽象方法
     *   - 函数式接口严格限制只能有一个抽象方法（可以有默认方法和静态方法）
     * 2. Lambda 表达式支持
     *   - 普通接口必须通过匿名内部类或具体实现类来实现
     *   - 函数式接口可以使用 Lambda 表达式简洁的实现
     * 3. 设计理念
     *   - 普通接口设计为定义一组相关方法的契约
     *   - 函数式接口设计为表示单一功能或行为，可以作为方法参数传递
     *
     * 函数式编程应用，在 ChatMemoryProvider 中
     * 1. 它使得代码更加简洁，避免了创建冗余的匿名内部类
     * 2. 它让行为（函数）可以作为参数传递
     * 3. 它促进了更具声明性而非命令性的编程风格
     * @return 这种设计让不同用户的聊天记忆可以根据其ID动态创建和隔离，同时保持代码的可读性和简洁性
     */
    @Bean
    ChatMemoryProvider chatMemoryProvider() {
        /**
         * 函数式接口实现，需要一个能接收 memoryId 并返回 ChatMemory 对象的方法
         * 这个 Lambda 表达式实现了上述的功能
         * 1. memoryId -> 定义了输入参数
         * 2. 箭头后面的代码定义了如何处理输入并返回结果
         */
        return memoryId -> MessageWindowChatMemory.builder()
                .id(memoryId)
                .chatMemoryStore(mongoChatMemoryStore) // 配置持久化对象
                .maxMessages(20)
                .build();

    }
    // 匿名内部类实现
        /*return new ChatMemoryProvider() {
            @Override
            public ChatMemory getChatMemory(Object memoryId) {
                return MessageWindowChatMemory.builder()
                        .id(memoryId)
                        .chatMemoryStore(mongoChatMemoryStore)
                        .maxMessages(20)
                        .build();
            }
        };*/
}
