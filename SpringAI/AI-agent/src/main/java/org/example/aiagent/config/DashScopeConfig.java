package org.example.aiagent.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 灵积大模型配置类
 */
@Configuration
@ConfigurationProperties(prefix = "spring.ai.dashscope")
public class DashScopeConfig {
    
    /**
     * DashScope API Key
     */
    private String apiKey;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
    
    @Override
    public String toString() {
        return "DashScopeConfig{" +
                "apiKey='" + (apiKey != null ? "已设置" : "未设置") + '\'' +
                '}';
    }
} 