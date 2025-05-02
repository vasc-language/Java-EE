package org.example.assistant;

import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-02
 * Time: 10:41
 */
// 假如在 配置文件中同时配置了多个大语言模型，需要在这里明确指定（explicit）模型的 beanName（qwenChatModel）
@AiService(
        wiringMode = AiServiceWiringMode.EXPLICIT,
        chatModel = "qwenChatModel"
)
public interface Assistant {
    String chat(String userMessage);
}
