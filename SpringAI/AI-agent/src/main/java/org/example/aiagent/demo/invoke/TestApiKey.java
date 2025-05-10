package org.example.aiagent.demo.invoke;

import org.example.aiagent.config.DashScopeConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * Description: API密钥管理组件
 * User: 姚东名
 * Date: 2025-05-10
 * Time: 22:35
 */
@Component
public class TestApiKey {
    // 备用的API Key，仅在开发环境使用，生产环境应该删除
    private static final String FALLBACK_API_KEY = "sk-dummy-key-for-dev-only";
    
    // 环境变量名称
    private static final String ENV_KEY_NAME = "AI_DASHSCOPE_API_KEY";
    
    // Spring ApplicationContext和Environment
    private static ApplicationContext applicationContext;
    private static Environment springEnv;
    
    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) {
        TestApiKey.applicationContext = applicationContext;
    }
    
    @Autowired
    public void setEnvironment(Environment environment) {
        TestApiKey.springEnv = environment;
    }
    
    /**
     * 获取API Key，按照以下优先级：
     * 1. Spring配置中的API Key（通过application.yml配置）
     * 2. Spring Environment中的变量（支持多种方式设置，如命令行参数、环境变量）
     * 3. 系统环境变量
     * 4. 备用值（仅开发环境）
     * 
     * @return 有效的API Key
     */
    public static String getApiKey() {
        String apiKey = null;
        
        // 1. 优先从Spring配置中获取
        if (applicationContext != null) {
            try {
                DashScopeConfig config = applicationContext.getBean(DashScopeConfig.class);
                apiKey = config.getApiKey();
                if (apiKey != null && !apiKey.isEmpty()) {
                    System.out.println("从Spring配置(application.yml)获取API Key成功");
                    return apiKey;
                }
            } catch (Exception e) {
                System.out.println("从Spring配置中获取API Key失败: " + e.getMessage());
            }
        }
        
        // 2. 从Spring Environment获取（支持多种配置源）
        if (springEnv != null) {
            apiKey = springEnv.getProperty(ENV_KEY_NAME);
            if (apiKey != null && !apiKey.isEmpty()) {
                System.out.println("从Spring Environment获取API Key成功");
                return apiKey;
            }
            
            // 尝试通过spring.ai.dashscope.api-key属性获取
            apiKey = springEnv.getProperty("spring.ai.dashscope.api-key");
            if (apiKey != null && !apiKey.isEmpty()) {
                System.out.println("从Spring属性spring.ai.dashscope.api-key获取API Key成功");
                return apiKey;
            }
        }
        
        // 3. 从系统环境变量中获取
        apiKey = System.getenv(ENV_KEY_NAME);
        if (apiKey != null && !apiKey.isEmpty()) {
            System.out.println("从系统环境变量获取API Key成功");
            return apiKey;
        }
        
        // 4. 使用备用值（警告：仅开发环境使用）
        System.out.println("警告: 无法从配置或环境变量获取API Key，使用备用值（仅用于开发环境）");
        return FALLBACK_API_KEY;
    }
    
    /**
     * 检查API Key是否有效
     * @param apiKey 要检查的API Key
     * @return 是否有效
     */
    public static boolean isValidApiKey(String apiKey) {
        return apiKey != null && !apiKey.isEmpty() && !apiKey.equals(FALLBACK_API_KEY);
    }
}
