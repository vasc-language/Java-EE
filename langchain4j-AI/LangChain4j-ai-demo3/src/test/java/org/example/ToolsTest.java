package org.example;

import org.example.assistant.SeparateChatAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-03
 * Time: 17:38
 */
@SpringBootTest
public class ToolsTest {
    @Autowired
    private SeparateChatAssistant separateChatAssistant;

    @Test
    public void testCalculatorTools() {
        String answer = separateChatAssistant.chat(8, "1+1等于几？475695037565的平方根等于几？");
        System.out.println(answer);
    }
}
