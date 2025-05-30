package org.example.aiagent.app;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-14
 * Time: 19:03
 */
@SpringBootTest
class LoveAppTest {
    @Autowired
    private LoveApp loveApp;
    @Test
    void doChat() {
        String chatId = UUID.randomUUID().toString();
        // 第一轮
        String message = "我是Join2049";
        String answer = loveApp.doChat(message, chatId);
        Assertions.assertNotNull(answer);

        // 第二轮
        String message2 = "我想知道你是否知道我的名字";
        String answer2 = loveApp.doChat(message2, chatId);
        Assertions.assertNotNull(answer);
    }

    /**
     * 测试结构化功能
     */
    @Test
    void doChatWithReport() {
        String chatId = UUID.randomUUID().toString();
        String message = "你好，我是 Join2049，但是现在我很烦我女盆友她，因为她太漂亮了，怕被别人抢走";
        LoveApp.LoveReport answer = loveApp.doChatWithReport(message, chatId);
        System.out.println(answer);
    }
}