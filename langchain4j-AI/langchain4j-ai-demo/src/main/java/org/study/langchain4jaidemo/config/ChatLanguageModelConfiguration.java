package org.study.langchain4jaidemo.config;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.study.langchain4jaidemo.service.Assistant;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-27
 * Time: 22:06
 */
@Configuration(proxyBeanMethods = false)
public class ChatLanguageModelConfiguration {
    @Bean
    public ChatLanguageModel chatLanguageModel() {
        return OpenAiChatModel.builder()
                .apiKey("sk-655663678dc1429f91becc5e5fb4e027")
                .modelName("deepseek-chat")
                .baseUrl("https://api.deepseek.com/v1")
                .build();
    }

    // 第二种方式 高阶 API AiServices
    @Bean
    public Assistant assistant(ChatLanguageModel chatLanguageModel) {
        return AiServices.create(Assistant.class, chatLanguageModel);
        // return (Assistant) assistant.chat("Hello");
        // System.out.println(answer); // Hello, how can I help you?
    }
}
