package org.example.aiagent.demo.invoke;// 建议dashscope SDK的版本 >= 2.12.0

import java.util.Arrays;
import java.lang.System;

import com.alibaba.dashscope.aigc.generation.Generation;
import com.alibaba.dashscope.aigc.generation.GenerationParam;
import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.alibaba.dashscope.common.Message;
import com.alibaba.dashscope.common.Role;
import com.alibaba.dashscope.exception.ApiException;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.alibaba.dashscope.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * DashScope SDK调用示例
 */
@Component
public class SdkAiInvoke {

    @Autowired
    private TestApiKey testApiKey; // 注入TestApiKey组件，确保Spring容器已初始化

    /**
     * 通过消息方式调用大模型
     */
    public GenerationResult callWithMessage() throws ApiException, NoApiKeyException, InputRequiredException {
        Generation gen = new Generation();
        Message systemMsg = Message.builder()
                .role(Role.SYSTEM.getValue())
                .content("You are a helpful assistant.")
                .build();
        Message userMsg = Message.builder()
                .role(Role.USER.getValue())
                .content("你是谁？")
                .build();
                
        // 获取API Key（优先从配置文件获取）
        String apiKey = TestApiKey.getApiKey();
        System.out.println("使用API Key调用大模型，API Key状态: " + (apiKey != null ? "有效" : "无效"));
        
        GenerationParam param = GenerationParam.builder()
                .apiKey(apiKey)
                .model("qwen-plus")
                .messages(Arrays.asList(systemMsg, userMsg))
                .resultFormat(GenerationParam.ResultFormat.MESSAGE)
                .build();
        return gen.call(param);
    }

    /**
     * 独立运行示例
     */
    public static void main(String[] args) {
        try {
            // 注意：在Spring容器外运行时，将无法从配置文件获取API Key
            // 会尝试从环境变量或默认值获取
            SdkAiInvoke invoke = new SdkAiInvoke();
            GenerationResult result = invoke.callWithMessage();
            System.out.println(JsonUtils.toJson(result));
        } catch (ApiException | NoApiKeyException | InputRequiredException e) {
            // 使用日志框架记录异常信息
            System.err.println("调用大模型服务时发生错误: " + e.getMessage());
        }
        System.exit(0);
    }
}
