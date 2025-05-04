package org.example;

import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.service.AiServices;
import org.example.assistant.Assistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-02
 * Time: 10:43
 */
@SpringBootTest
public class AIServiceTest {
    @Autowired
    private QwenChatModel qwenChatModel;

    @Test
    public void testChat() {
        // 手动创建 AiServices
        // return builder(aiService).chatLanguageModel(chatLanguageModel).build();
        Assistant assistant = AiServices.create(Assistant.class, qwenChatModel);
        // 调用 service 的接口
        String answer = assistant.chat("Who are you~");
        System.out.println(answer);
    }


    /**
     * AiServices 工作原理
     * 它会组装 Assistant 接口以及其他组件，并使用反射机制创建一个实现 Assistant 接口的代理对象
     * 这个代理对象会处理输入和输出的所有转换工作。在这个例子中，chat方法的输入是一个字符串，但是大
     * 模型需要一个 UserMessage 对象。所以，代理对象将这个字符串转换为UserMessage，并调用聊天语
     * 言模型。chat方法的输出类型也是字符串，但是大模型返回的是AiMes sage 对象，代理对象会将其转换
     * 为字符串。
     * 简单理解就是：代理对象的作用是输入转换和输出转换
     */
    @Autowired
    private Assistant assistant;
    @Test
    public void testChat2() {
        String answer = assistant.chat("Who are you~");
        System.out.println(answer);
    }
}
