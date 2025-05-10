package org.example.aiagent.service;

import com.alibaba.dashscope.aigc.generation.Generation;
import com.alibaba.dashscope.aigc.generation.GenerationParam;
import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.alibaba.dashscope.common.Message;
import com.alibaba.dashscope.common.Role;
import com.alibaba.dashscope.exception.ApiException;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import org.example.aiagent.config.DashScopeConfig;
import org.example.aiagent.demo.invoke.TestApiKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * DashScope大模型服务类
 * 推荐在Spring应用中使用此服务类调用DashScope API，而不是直接使用SdkAiInvoke
 */
@Service
public class DashScopeService {

    @Autowired
    private DashScopeConfig dashScopeConfig;
    
    @Autowired
    private Environment environment;

    /**
     * 调用大模型进行对话
     * @param userMessage 用户消息
     * @return 大模型响应结果
     */
    public GenerationResult chat(String userMessage) throws ApiException, NoApiKeyException, InputRequiredException {
        // 默认系统提示语
        Message systemMsg = Message.builder()
                .role(Role.SYSTEM.getValue())
                .content("You are a helpful assistant.")
                .build();
        
        // 用户消息
        Message userMsg = Message.builder()
                .role(Role.USER.getValue())
                .content(userMessage)
                .build();
        
        return chat(Arrays.asList(systemMsg, userMsg));
    }
    
    /**
     * 调用大模型进行对话
     * @param messages 消息列表
     * @return 大模型响应结果
     */
    public GenerationResult chat(List<Message> messages) throws ApiException, NoApiKeyException, InputRequiredException {
        Generation gen = new Generation();
        
        // 获取API Key，多种来源尝试
        // 1. 从配置类获取（application.yml中配置）
        String apiKey = dashScopeConfig.getApiKey();
        
        // 2. 如果配置类获取失败，使用TestApiKey工具类获取（包含环境变量等多种来源）
        if (apiKey == null || apiKey.isEmpty()) {
            apiKey = TestApiKey.getApiKey();
            // 记录日志，帮助诊断问题
            System.out.println("从配置类获取API Key失败，使用TestApiKey获取：" + 
                    (TestApiKey.isValidApiKey(apiKey) ? "成功" : "使用备用值"));
        }
        
        // 验证API Key是否存在
        if (apiKey == null || apiKey.isEmpty()) {
            System.err.println("API Key不存在，请配置环境变量AI_DASHSCOPE_API_KEY或在application.yml中设置spring.ai.dashscope.api-key");
            throw new NoApiKeyException();
        }
        
        GenerationParam param = GenerationParam.builder()
                .apiKey(apiKey)
                .model("qwen-plus")
                .messages(messages)
                .resultFormat(GenerationParam.ResultFormat.MESSAGE)
                .build();
                
        return gen.call(param);
    }
} 