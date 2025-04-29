package org.study.langchain4jaidemo.controller;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ImageContent;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.output.Response;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Base64;

/**
 * 图像识别控制器
 */
@Slf4j
@RequestMapping("/ChatLanguageOnImageModelController")
@RestController
public class ChatLanguageOnImageModelController {
    @Resource(name = "chatLanguageModel3")
    private ChatLanguageModel chatLanguageModel;

    @Value("classpath:static/images/img.png")
    private org.springframework.core.io.Resource resource;

    @GetMapping("/imageCall")
    public String readImageContent() throws IOException {
        // 获取图片文件并转换为Base64
        byte[] contentAsByteArray = resource.getContentAsByteArray();
        String base64Data = Base64.getEncoder().encodeToString(contentAsByteArray);

        log.info("正在处理图片并发送至模型...");

        // 创建包含文本和图片的用户消息
        UserMessage userMessage = UserMessage.from(
                TextContent.from("从以下图片中获取来源网站名称和9.27号上证指数"),
                ImageContent.from(base64Data, "image/png")
        );

        try {
            // 两种方法都行不通
            // 方法1：直接使用generate方法
            /*Response<AiMessage> response = chatLanguageModel.generate(userMessage);
            String responseText = response.content().text();

            log.info("模型响应: {}", responseText);*/
            return null;
            
            /* 方法2：如果上面不行，可以尝试这种方式
            List<ChatMessage> messages = new ArrayList<>();
            messages.add(userMessage);
            Response<AiMessage> response = chatLanguageModel.generate(messages);
            return response.content().text();
            */
        } catch (Exception e) {
            log.error("处理图片或调用模型时出错", e);
            return "处理失败: " + e.getMessage();
        }
    }
}