package org.example.aiagent.Advisor;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.api.AdvisedRequest;
import org.springframework.ai.chat.client.advisor.api.AdvisedResponse;
import org.springframework.ai.chat.client.advisor.api.CallAroundAdvisorChain;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-15
 * Time: 18:26
 */
@SpringBootTest
class ReReadingAdvisorTest {
    @Autowired
    private ReReadingAdvisor reReadingAdvisor;

    @Resource
    private ChatModel dashscopeChatModel;

    @Test
    void aroundCall() {
        // 创建模拟请求对象
        String testQuery = "这是一个测试问题";
        AdvisedRequest request = AdvisedRequest.builder()
                .userText(testQuery)
                .chatModel(dashscopeChatModel)
                .build();

        // 修改测试代码中的这部分
        CallAroundAdvisorChain chain = modifiedRequest -> {
            // 验证请求是否被正确修改
            assertTrue(modifiedRequest.userText().contains("Read the question again"));
            assertTrue(modifiedRequest.userParams().containsKey("re2_input_query"));
            assertEquals(testQuery, modifiedRequest.userParams().get("re2_input_query"));

            // 返回一个模拟的响应，添加必需的adviseContext参数
            return AdvisedResponse.builder()
                    .adviseContext(new HashMap<>()) // 添加这行，提供一个空的Map作为adviseContext
                    .build();
        };

        // 执行aroundCall方法
        AdvisedResponse response = reReadingAdvisor.aroundCall(request, chain);

        // 验证响应不为空
        assertNotNull(response);
    }
}