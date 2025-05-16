package org.example.aiagent.Advisor.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 敏感词检查工具类
 */
public class SensitiveWordsUtil {
    
    // 默认敏感词列表，实际应用中可从配置文件或数据库加载
    private static final Set<String> DEFAULT_SENSITIVE_WORDS = new HashSet<>(Arrays.asList(
            "暴力", "色情", "赌博", "毒品", "政治", "欺诈", "违法"
    ));
    
    private static Set<String> sensitiveWords = new HashSet<>(DEFAULT_SENSITIVE_WORDS);
    
    /**
     * 检查文本是否包含敏感词
     *
     * @param text 待检查文本
     * @return 如果包含敏感词返回敏感词，否则返回null
     */
    public static String checkSensitiveWords(String text) {
        if (text == null || text.isEmpty()) {
            return null;
        }
        
        for (String word : sensitiveWords) {
            if (text.contains(word)) {
                return word;
            }
        }
        
        return null;
    }
    
    /**
     * 添加敏感词
     *
     * @param word 敏感词
     */
    public static void addSensitiveWord(String word) {
        sensitiveWords.add(word);
    }
    
    /**
     * 移除敏感词
     *
     * @param word 敏感词
     */
    public static void removeSensitiveWord(String word) {
        sensitiveWords.remove(word);
    }
    
    /**
     * 重置敏感词列表
     */
    public static void resetSensitiveWords() {
        sensitiveWords = new HashSet<>(DEFAULT_SENSITIVE_WORDS);
    }
    
    /**
     * 获取所有敏感词
     *
     * @return 敏感词集合
     */
    public static Set<String> getAllSensitiveWords() {
        return new HashSet<>(sensitiveWords);
    }
} 