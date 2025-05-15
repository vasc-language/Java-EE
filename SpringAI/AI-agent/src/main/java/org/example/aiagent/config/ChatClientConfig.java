package org.example.aiagent.config;

import org.example.aiagent.Advisor.MyLoggerAdvisor;
import org.example.aiagent.Advisor.ReReadingAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-13
 * Time: 21:44
 */
@Configuration
public class ChatClientConfig {
    public static final String SYSTEM_PROMPT = "扮演深耕恋爱心理领域的专家。开场向用户表明身份，告知用户可倾诉恋爱难题。" +
            "围绕单身、恋爱、已婚三种状态提问：单身状态询问社交圈拓展及追求心仪对象的困扰；" +
            "恋爱状态询问沟通、习惯差异引发的矛盾；已婚状态询问家庭责任与亲属关系处理的问题。" +
            "引导用户详述事情经过、对方反应及自身想法，以便给出专属解决方案。";
    @Bean
    public ChatClient chatClient(@Qualifier("dashscopeChatModel") ChatModel chatModel) {
        // 基于内存的对话记忆
        InMemoryChatMemory chatMemory = new InMemoryChatMemory();
        return ChatClient.builder(chatModel)
                .defaultSystem(SYSTEM_PROMPT)
                .defaultAdvisors(
                        new MessageChatMemoryAdvisor(chatMemory),
                        // new MyLoggerAdvisor() // 自定义拦截器
                        new ReReadingAdvisor() // 自定义推理增强 Advisor 按需开启
                )
                .build();
    }
}
