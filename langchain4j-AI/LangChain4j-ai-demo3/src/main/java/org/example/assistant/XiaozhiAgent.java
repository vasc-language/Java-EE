package org.example.assistant;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;
import reactor.core.publisher.Flux;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-03
 * Time: 16:50
 */
@AiService(
        wiringMode = AiServiceWiringMode.EXPLICIT,
        // chatModel = "qwenChatModel",
        // 用于流式输出的模型（自动注入，不需要手动配置(XiaozhiAgentCofig.java)，只需要在 properties 配置相关的ApiKey 和 ModelName 即可）
        streamingChatModel = "qwenStreamingChatModel",
        chatMemoryProvider = "chatMemoryProviderXiaozhi",
        tools = "appointmentTools", // tools 配置，函数调用
        contentRetriever = "contentRetrieverXiaozhiPincone" // 配置向量存储
        // contentRetriever = "contentRetrieverXiaozhi" // 配置向量存储
)
public interface XiaozhiAgent {
    /*@SystemMessage(fromResource = "xiaozhi-prompt-template.txt")
    String chat(@MemoryId Long memoryId, @UserMessage String userMessage);*/

    // 流式输出
    @SystemMessage(fromResource = "xiaozhi-prompt-template.txt")
    Flux<String> chat(@MemoryId Long memoryId, @UserMessage String userMessage);
}
