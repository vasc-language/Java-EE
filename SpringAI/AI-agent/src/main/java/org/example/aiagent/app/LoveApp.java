package org.example.aiagent.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.example.aiagent.config.ChatClientConfig.SYSTEM_PROMPT;
import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY;
import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_MEMORY_RETRIEVE_SIZE_KEY;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-14
 * Time: 18:34
 */
@Slf4j
@Component
public class LoveApp {
    @Autowired
    private ChatClient chatClient;
    
    public LoveApp(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    /**
     * AI 基础对话（支持多轮对话记忆）
     * @param message 用户消息
     * @param chatId 聊天ID
     * @return
     */
    public String doChat(String message, String chatId) {
        ChatResponse response = chatClient
                .prompt()
                .user(message)
                .advisors(spec -> spec.param(CHAT_MEMORY_CONVERSATION_ID_KEY, chatId)
                        .param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 10))
                .call()
                .chatResponse();
        String content = response.getResult().getOutput().getText();
        log.info("content: {}", content);
        return content;
    }

    /**
     * 定义恋爱报告
     * @param title
     * @param suggestions
     */
    record LoveReport(String title, List<String> suggestions) {
        // 标题 + 建议
        // {"title": "", "suggestion": ""}
    }

    /**
     * 1. 额外补充系统提示词
     * 2. 增加结构化功能
     * @param message
     * @param chatId
     * @return
     */
    public LoveReport doChatWithReport(String message, String chatId) {
        LoveReport loveReport = chatClient
                .prompt()
                .system(SYSTEM_PROMPT + "每次对话都生成恋爱报告，标题为{用户名}的恋爱报告，内容建议为列表")
                .user(message)
                // param 是拦截器Advisor配置过程设置参数的关键方法。通常以 lambda 表达式调用
                // 参数传递：向Advisor 传递特定参数，控制 Advisor 的行为
                // 上下文是设置：在聊天上下文中设置键值对，供后续处理使用
                .advisors(spec -> spec.param(CHAT_MEMORY_CONVERSATION_ID_KEY, chatId)
                        .param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 10))
                .call() // 通过 call() 执行请求并获取 AI 响应
                .entity(LoveReport.class); // 将响应转换成 LoveReport 对象（Java 记录类）
        log.info("loveReport: {}", loveReport);
        return loveReport;
    }

}
