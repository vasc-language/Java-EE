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
 * Date: 2025-05-03
 * Time: 8:49
 */
@AiService(
        wiringMode = AiServiceWiringMode.EXPLICIT,
        chatModel = "qwenChatModel",
        chatMemory = "chatMemory",
        chatMemoryProvider = "chatMemoryProvider",
        tools = "calculatorTools" // 配置 Tools 工具
)
public interface SeparateChatAssistant {
    /**
     * 根据用户的ID 和用户消息隔离用户聊天记录
     * @param memoryId 聊天的ID
     * @param userMessage 用户消息
     * @SystemMessage 的内容将在后台转换为 SystemMessage 对象，并与 UserMessage 一起发送给大模型
     * @return
     */
    // @SystemMessage("你是我的好朋友，请用粤语回答我的问题。今日是{{current_date}}") // 系统提示词，后面的{{}} 相当于占位符
    @SystemMessage(fromResource = "my-prompt-template.txt")
    String chat(@MemoryId int memoryId, @UserMessage String userMessage);

    // 如果有两个或两个以上的参数，必须使用 @V
    @UserMessage("你的我的好朋友，请用粤语回答问题。{{userMessage}}") // 绑定：用户的问题 + 用户提示词 一起发送给大模型
    String chat2(@MemoryId int memoryId, @V("userMessage") String userMessage);


    // 将 @SystemMessage 和 @V 一起使用（系统提示词 + 用户提示词）
    @SystemMessage(fromResource = "my-prompt-template2.txt")
    String chat3(
            @MemoryId int memoryId,
            @UserMessage String userMessage,
            @V("username") String username,
            @V("age") int age
    );
}
