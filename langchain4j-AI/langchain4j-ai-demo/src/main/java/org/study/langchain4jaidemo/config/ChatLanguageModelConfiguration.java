package org.study.langchain4jaidemo.config;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.study.langchain4jaidemo.listener.TestChatModelListener;
import org.study.langchain4jaidemo.service.Assistant;

import java.time.Duration;
import java.util.List;

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

    @Bean
    public ChatLanguageModel chatLanguageModel2() {
        // 专门进行监听测试
        return OpenAiChatModel.builder()
                .apiKey(System.getenv("LANGCHAIN4J_KEY"))
                .modelName("qwen-turbo")
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .logRequests(true) // 日志级别设置为 debug 才有效
                .logResponses(true) // 日志级别设置为 debug 才有效
                .listeners(List.of(new TestChatModelListener())) // 手动实现的监听器
                .maxRetries(2) // 重试机制
                .timeout(Duration.ofSeconds(10)) // 设置超时时间为 10s
                .build();
    }

    @Bean
    public ChatLanguageModel chatLanguageModel3() {
        return OpenAiChatModel
                .builder()
                .apiKey(System.getenv("LANGCHAIN4J_KEY"))
                .modelName("qwen2.5-vl-72b-instruct")
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .build();
    }

    @Bean
    public StreamingChatLanguageModel streamingChatLanguageModel() {
        return OpenAiStreamingChatModel.builder()
                .apiKey(System.getenv("LANGCHAIN4J_KEY"))
                .modelName("qwen-turbo")
                .timeout(Duration.ofSeconds(10)) // 超时时间设置为 10s
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .build();
    }

    // 高阶API 时使用 AiServices
    @Bean
    public Assistant chatAssistant(StreamingChatLanguageModel streamingChatLanguageModel) {
        return AiServices.create(Assistant.class, streamingChatLanguageModel);
    }

}
