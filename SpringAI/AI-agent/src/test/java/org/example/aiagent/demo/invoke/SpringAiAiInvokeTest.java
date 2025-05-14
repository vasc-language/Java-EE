package org.example.aiagent.demo.invoke;

import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import reactor.core.publisher.Flux;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-13
 * Time: 20:19
 */
@SpringBootTest
class SpringAiAiInvokeTest {

    @Autowired
    private ChatClient chatClient;

    /**
     * ChatClient 返回 ChatResponse 对象
     */
    @Test
    void testChatClientWithChatResponse() {
        // 使用配置类中创建的ChatClient实例
        ChatResponse chatResponse = chatClient.prompt()
            .user("给我讲个笑话！")
            .call()
            .chatResponse();
        
        assertNotNull(chatResponse);
        System.out.println("响应内容: " + chatResponse.getResult().getOutput().getText());
    }

    /**
     * 返回实体对象（自动将 AI 映射为 Java 对象）
     * 这是单个实体
     */
    @Test
    void testChatClientWithEntity() {
        // 定义实体记录类
        record ActorFilms(String actor, List<String> movies) {}
        
        // 使用配置类中创建的ChatClient实例
        ActorFilms actorFilms = chatClient.prompt()
            .user("生成一个随机演员的电影列表")
            .call()
            .entity(ActorFilms.class);
            
        assertNotNull(actorFilms);
        System.out.println("演员: " + actorFilms.actor() + ", 电影: " + actorFilms.movies());
    }

    /**
     * 返回实体对象：返回泛型集合
     */
    @Test
    void testChatClientWithEntityList() {
        // 定义实体记录类
        record ActorFilms(String actor, List<String> movies) {}
        
        // 使用配置类中创建的ChatClient实例
        List<ActorFilms> multipleActors = chatClient.prompt()
            .user("生成汤姆·汉克斯和比尔·默里的电影列表")
            .call()
            .entity(new ParameterizedTypeReference<List<ActorFilms>>() {}); // 反序列化 AI 大模型发送过来的 JSON 格式转化为 Java 对象
            
        assertNotNull(multipleActors);
        multipleActors.forEach(actor -> 
            System.out.println("演员: " + actor.actor() + ", 电影: " + actor.movies()));
    }

    /**
     * 流式返回
     */
    @Test
    void testChatClientWithStreamResponse() {
        // 使用配置类中创建的ChatClient实例
        Flux<String> streamResponse = chatClient.prompt()
            .user("讲一个故事")
            .stream()
            .content();
            
        // 订阅流并打印每一部分内容
        streamResponse.subscribe(
            content -> System.out.println("收到内容: " + content),
            error -> System.err.println("错误: " + error),
            () -> System.out.println("流结束")
        );
        
        // 等待一段时间让流完成
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}