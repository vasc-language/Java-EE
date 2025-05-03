package org.example.assistant;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-02
 * Time: 11:26
 */
@AiService(
        wiringMode = AiServiceWiringMode.EXPLICIT,
        chatModel = "qwenChatModel",
        chatMemory = "chatMemory" //需要在配置类中实现
)
public interface MemoryChatAssistant {
    @UserMessage("你是我的好朋友，请用上海话回答我的问题，并添加一些微笑的表情符号。{{it}}") // {{it}} 表示这里唯一的参数的占位符
    String chat(String userMessage);

    @UserMessage("你是我的好朋友，请用上海话回答我的问题，并添加一些微笑的表情符号。{{message}}")
    String chat2(@V("message") String userMessage); // 明确指定的参数名称
}
