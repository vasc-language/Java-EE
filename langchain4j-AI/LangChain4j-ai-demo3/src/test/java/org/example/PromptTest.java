package org.example;

import org.example.assistant.MemoryChatAssistant;
import org.example.assistant.SeparateChatAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-03
 * Time: 11:16
 */
@SpringBootTest
public class PromptTest {
    @Autowired
    private SeparateChatAssistant separateChatAssistant;

    /**
     * 测试系统提示词
     */
    @Test
    public void testSystemMessage() {
        String answer1 = separateChatAssistant.chat(3, "广东有什么地域特色美食");
        System.out.println(answer1);
    }

    /**
     * 测试从资源中加载提示词模板 my-prompt-template.txt
     */
    @Test
    public void testSystemMessage2() {
        String answer1 = separateChatAssistant.chat(4, "东北有什么地域特色美食");
        System.out.println(answer1);

        String answer2 = separateChatAssistant.chat(4, "哈尔滨有什么地域特色美食");
        System.out.println(answer2);
    }

    /**
     * 测试@V
     * @UserMessage 中的内容每次都会被和用户问题组织在一起发送给大到模型
     */
    @Test
    public void testV() {
        String answer1 = separateChatAssistant.chat2(5, "我是Join2049");
        System.out.println(answer1);

        String answer2 = separateChatAssistant.chat2(5, "我是谁~");
        System.out.println(answer2);
    }

    /**
     * 测试@SystemMessage + @V + @UserMessage 一起使用
     */
    @Test
    public void testUserInfo() {
        String answer = separateChatAssistant.chat3(6, "我是谁？多大了~", "张天琦", 19);
        System.out.println(answer);
    }


    @Autowired
    private MemoryChatAssistant memoryChatAssistant;
    /**
     * 测试{{it}}
     */
    @Test
    public void testUserMessage() {
        String answer = memoryChatAssistant.chat("我是Join2049");
        System.out.println(answer);
    }

    @Test
    public void testUserMessage2() {
        String answer1 = memoryChatAssistant.chat("我是Join2049");
        System.out.println(answer1);

        String answer2 = memoryChatAssistant.chat("我今年19了");
        System.out.println(answer2);

        String answer3 = memoryChatAssistant.chat("你知道我是谁吗，多大了");
        System.out.println(answer3);
    }
}
