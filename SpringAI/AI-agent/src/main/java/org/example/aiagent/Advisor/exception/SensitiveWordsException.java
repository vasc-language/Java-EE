package org.example.aiagent.Advisor.exception;

/**
 * 敏感词检查异常
 */
public class SensitiveWordsException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    
    private final String sensitiveContent;
    
    public SensitiveWordsException(String sensitiveContent) {
        super("内容包含敏感词");
        this.sensitiveContent = sensitiveContent;
    }
    
    public SensitiveWordsException(String message, String sensitiveContent) {
        super(message);
        this.sensitiveContent = sensitiveContent;
    }
    
    public SensitiveWordsException(String message, String sensitiveContent, Throwable cause) {
        super(message, cause);
        this.sensitiveContent = sensitiveContent;
    }
    
    public String getSensitiveContent() {
        return sensitiveContent;
    }
} 