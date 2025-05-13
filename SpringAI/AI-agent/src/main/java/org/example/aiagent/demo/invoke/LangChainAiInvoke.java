package org.example.aiagent.demo.invoke;

import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.model.chat.ChatLanguageModel;

/**
 * Created with IntelliJ IDEA.
 * Description: 使用 LangChain4j 框架
 * User: 姚东名
 * Date: 2025-05-13
 * Time: 20:39
 */

public class LangChainAiInvoke {
    public static void main(String[] args) {
        ChatLanguageModel qwenModel = QwenChatModel
                .builder()
                .apiKey(TestApiKey.getApiKey())
                .modelName("qwen-max")
                .build();
        String answer = qwenModel.chat("我是Join2049，你觉得这个名字蕴意好吗？好在哪？");

        System.out.println(answer);
    }
}
