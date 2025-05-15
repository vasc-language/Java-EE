/*
package org.example.aiagent.ConverterTest;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

*/
/**
 * 测试 Spring AI 的输出转换功能
 * 演示如何将AI生成的内容自动转换为Java对象
 *//*

@SpringBootTest
public class ActorsFilmsTest {

    @Resource
    private ChatModel chatModel;

    // 定义一个记录类，Spring AI 会自动尝试将输出映射到这个类
    record ActorsFilms(String actor, List<String> movies) {}

    @Test
    @DisplayName("测试将ChatClient响应转换为ActorsFilms对象")
    void testChatClientWithActorsFilms() {
        // 使用高级 ChatClient API
        ActorsFilms actorsFilms = ChatClient.create(chatModel).prompt()
                .user("Generate 5 movies for Tom Hanks.")
                .call()
                .entity(ActorsFilms.class);

        // 验证结果
        assertNotNull(actorsFilms, "返回的ActorsFilms对象不应为null");
        assertEquals("Tom Hanks", actorsFilms.actor(), "演员名应为Tom Hanks");
        assertNotNull(actorsFilms.movies(), "电影列表不应为null");
        assertEquals(5, actorsFilms.movies().size(), "应返回5部电影");
        
        // 验证每部电影名称都是非空字符串
        actorsFilms.movies().forEach(movie -> {
            assertNotNull(movie, "电影名不应为null");
            assertFalse(movie.isBlank(), "电影名不应为空字符串");
        });
        
        // 打印结果
        System.out.println("===== ActorsFilms 转换测试结果 =====");
        System.out.println("演员: " + actorsFilms.actor());
        System.out.println("电影列表:");
        for (int i = 0; i < actorsFilms.movies().size(); i++) {
            System.out.println((i + 1) + ". " + actorsFilms.movies().get(i));
        }
        System.out.println("==================================");
    }
    
    @Test
    @DisplayName("使用格式指导提示转换为ActorsFilms对象")
    void testChatClientWithFormattedPrompt() {
        // 使用格式指导的提示，确保AI输出可以正确映射到ActorsFilms
        String prompt = """
                Generate information about Tom Hanks and 5 of his most famous movies.
                Format the response exactly as follows (JSON format):
                {
                    "actor": "Tom Hanks",
                    "movies": [
                        "Movie 1",
                        "Movie 2",
                        "Movie 3",
                        "Movie 4",
                        "Movie 5"
                    ]
                }
                """;
        
        ActorsFilms actorsFilms = ChatClient.create(chatModel).prompt()
                .user(prompt)
                .call()
                .entity(ActorsFilms.class);

        // 验证结果
        assertNotNull(actorsFilms, "返回的ActorsFilms对象不应为null");
        assertEquals("Tom Hanks", actorsFilms.actor(), "演员名应为Tom Hanks");
        assertNotNull(actorsFilms.movies(), "电影列表不应为null");
        assertEquals(5, actorsFilms.movies().size(), "应返回5部电影");
        
        // 打印结果
        System.out.println("===== 格式化提示测试结果 =====");
        System.out.println("演员: " + actorsFilms.actor());
        System.out.println("电影列表:");
        for (int i = 0; i < actorsFilms.movies().size(); i++) {
            System.out.println((i + 1) + ". " + actorsFilms.movies().get(i));
        }
        System.out.println("=============================");
    }
    
    @Test
    @DisplayName("使用构建器模式和系统提示指导输出格式")
    void testChatClientWithSystemPrompt() {
        // 使用构建器模式创建ChatClient，并添加系统提示
        String systemPrompt = """
                你是一个专业的电影信息助手。当用户询问某个演员的电影时，你需要以特定格式回答。
                回答格式必须是JSON，包含actor字段和movies数组字段。
                示例格式：
                {
                    "actor": "演员名",
                    "movies": ["电影1", "电影2", "电影3", "电影4", "电影5"]
                }
                只返回JSON内容，不要添加其他文字。
                """;
                
        ChatClient chatClient = ChatClient.builder(chatModel)
                .defaultSystem(systemPrompt)
                .build();
                
        ActorsFilms actorsFilms = chatClient.prompt()
                .user("给我列出汤姆·汉克斯(Tom Hanks)的5部经典电影")
                .call()
                .entity(ActorsFilms.class);
                
        // 验证结果
        assertNotNull(actorsFilms, "返回的ActorsFilms对象不应为null");
        assertEquals("Tom Hanks", actorsFilms.actor(), "演员名应为Tom Hanks");
        assertNotNull(actorsFilms.movies(), "电影列表不应为null");
        assertEquals(5, actorsFilms.movies().size(), "应返回5部电影");
        
        // 打印结果
        System.out.println("===== 系统提示测试结果 =====");
        System.out.println("演员: " + actorsFilms.actor());
        System.out.println("电影列表:");
        for (int i = 0; i < actorsFilms.movies().size(); i++) {
            System.out.println((i + 1) + ". " + actorsFilms.movies().get(i));
        }
        System.out.println("============================");
    }
} */
